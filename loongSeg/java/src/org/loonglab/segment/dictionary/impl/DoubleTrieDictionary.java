package org.loonglab.segment.dictionary.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loonglab.segment.SegmentException;
import org.loonglab.segment.dictionary.Dictionary;
import org.loonglab.segment.dictionary.Hit;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.util.ChineseEncoder;

import sun.util.calendar.BaseCalendar;

public class DoubleTrieDictionary implements Dictionary {
	
	static Log log = LogFactory.getLog(DoubleTrieDictionary.class);
	
	//int initCapacity;
	
	ArrayList<BaseNode> baseArray;
	ArrayList<Integer> checkArray;
	
	long searchIdleTime=0;
	
	int minIdleNode=0;
	
	public void loadDicFromFile(String fileName){
		List<WordItem> allWords=new ArrayList<WordItem>();
		
		try {
			//1、将词典文件读入数组
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK"));

			String wordStr;
			int maxWordLength=0;
			WordItem lastWordItem=null;
			while((wordStr=br.readLine())!=null){
				String[] words=wordStr.split(" ");
				if(lastWordItem!=null&&lastWordItem.getWord().equals(words[0])){
					lastWordItem.addPos(new Integer(words[2]),new Integer(words[1]));
				}
				else{
					WordItem wi=new WordItem(words[0],new Integer(words[2]),new Integer(words[1]));
					allWords.add(wi);
					lastWordItem=wi;
					
					if(words[0].length()>maxWordLength)
						maxWordLength=words[0].length();
				}
				
				
				
			}
					
			
			br.close();
			
			//2、遍历数组，构造双数组Trie树
			buildTrieTree(allWords,maxWordLength);
			
			log.debug("=======search idle time is "+searchIdleTime);
			
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
			throw new SegmentException(e1.getMessage(),e1);
		}
	}
	
	
	
	private void buildTrieTree(List<WordItem> allWords,int maxWordLength) {

		
		//TODO 相同词，不同词性的要进行处理 ok
		//TODO 对二元词表的处理
		//TODO 解决共享前缀的问题 ok
		//TODO base和check直接用数组实现
		//TODO 设置成词标志 ok
		//TODO 将无分支的节点提出来
		//TODO 优化查找空闲空间
		
		//对allWords进行排序
		Collections.sort(allWords);
		
		log.debug(allWords.size()+" word sort finish...");
		
		//第一次遍历，把首字放到Base数组中
		
		int firstSize=0;
		for (WordItem wordItem : allWords) {
			char c=wordItem.getWord().charAt(0);
			int key=ChineseEncoder.hashCode(c);
			
			//判断重复的首字
			BaseNode bn=baseArray.get(key);			
			if(bn==null){
				bn=new BaseNode(1,c);				
				baseArray.set(key, bn);
				checkArray.set(key, 0);
				
				firstSize++;
			}
			
			if(wordItem.getWord().length()==1)
				bn.setWordItem(wordItem);
			

			
			
		}
		
		log.debug("first size is "+firstSize);
		log.debug("first char save into double array...");
		
	
		//再进行maxWordLength-1次遍历allWords，将每个词放到双数组中
		for (int i = 1; i < maxWordLength; i++) {
			
			//将lastCharIndex数组清零
			//Arrays.fill(lastCharIndex, 0);
			
			//int lastkey=-1;
			log.debug("===========i is "+i+"==============");
			String lastPrefix="";
			List<Integer> childKeys=new ArrayList<Integer>();
			List<Character> childChars=new ArrayList<Character>();
			List<WordItem> wiList=new ArrayList<WordItem>();
			for (int j = 0; j < allWords.size(); j++) {
				WordItem wi = allWords.get(j);				
				if(wi.getWord().length()>i){
					//char lastChar=wi.getWord().charAt(i-1);
					String curPrefix=wi.getWord().substring(0, i);
					char curChar=wi.getWord().charAt(i);
					
					//int key=ChineseEncoder.hashCode(lastChar);
					int curkey=ChineseEncoder.hashCode(curChar);
					
//					if(lastkey==-1){
//						lastkey=key;
//					}
					
//					log.debug("curPrefix="+curPrefix+",lastPrefix="+lastPrefix);
					
					if(lastPrefix.equals(""))
						lastPrefix=curPrefix;
					
					if(curPrefix.equals(lastPrefix)){
						if(!childKeys.contains(curkey)){
							childKeys.add(curkey);
							childChars.add(curChar);
							
							if(wi.getWord().length()==i+1){
								wiList.add(wi);
								//log.debug("+++wi is "+wi);
							}							
							else
								wiList.add(null);
						}
						
					}
					else{
						buildSubTree(lastPrefix, childKeys, childChars, wiList);
						
						
						//lastkey=key;
						lastPrefix=curPrefix;
						childKeys.clear();
						childKeys.add(curkey);
						childChars.clear();
						childChars.add(curChar);
						
						wiList.clear();
						if(wi.getWord().length()==i+1){
							//log.debug("wi is "+wi);
							wiList.add(wi);
						}
							
						else
							wiList.add(null);
						
						
					}
					
					
					
					
					
					
				}
				
				if(j==allWords.size()-1){
					buildSubTree(lastPrefix, childKeys, childChars, wiList);
				}
				
				
				
				
			}
		}
	}



	private void buildSubTree(String lastPrefix, List<Integer> childKeys,
			List<Character> childChars, List<WordItem> wiList) {
		
		//==========开始处理childKeys=============
		//（1）搜索前缀
//		log.debug("=====lastPrefix is "+lastPrefix+"======");
		char first=lastPrefix.charAt(0);
		int lkey=ChineseEncoder.hashCode(first);
		//log.debug("lkey("+lkey+") char is "+first);
		for (int k = 1; k < lastPrefix.length(); k++) {
			BaseNode bn=baseArray.get(lkey);
			

			char cChar=lastPrefix.charAt(k);
			int ckey=ChineseEncoder.hashCode(cChar);
			lkey=bn.getValue()+ckey;
			bn=baseArray.get(lkey);
			//log.debug("lkey("+lkey+") char is "+bn.getNodeChar());
		}
		
		
		
		//（2）查找是否有空闲空间
		//int lastK=lastCharIndex[lastkey];
		BaseNode bn=baseArray.get(lkey);	
		int k=10000;
		
		boolean success=false;
		
		long startTime=System.currentTimeMillis();
		//if(childKeys.size()>1){
			while(!success){
				success=true;
				k++;
				for (Integer childkey : childKeys) {
					
						if(baseArray.get(k+childkey)!=null||checkArray.get(k+childkey)!=null){
							success=false;
							break;
						}

				}
				
				
			}
			searchIdleTime=searchIdleTime+(System.currentTimeMillis()-startTime);
			//log.debug("search idle space cost "+(System.currentTimeMillis()-startTime));
			
			//（3）放置子节点(TODO childkeys可以用set来表示）
			bn.setValue(k);
			for (int h = 0; h < childKeys.size(); h++) {
				int childkey=childKeys.get(h);
				char childChar=childChars.get(h);
				BaseNode node=baseArray.get(k+childkey);
				if(node==null){
					node=new BaseNode(1,childChar);								
					baseArray.set(k+childkey,node);
					checkArray.set(k+childkey, lkey);
					
//					if((k+childkey)>minIdleNode){
//						minIdleNode=(k+childkey);
//					}
				}
				
				if(node.getWordItem()==null){
					node.setWordItem(wiList.get(h));
				}

				

				
				
			}
		//}
		
		
		
	}


	
	
	
	public DoubleTrieDictionary() {
		this(1000);
	}



	public DoubleTrieDictionary(int initCapacity) {
		
		//this.initCapacity=initCapacity;
		
		baseArray=new ArrayList<BaseNode>(initCapacity);
		checkArray=new ArrayList<Integer>(initCapacity);
		
		for (int i = 0; i < initCapacity; i++) {
			baseArray.add(null);
			checkArray.add(null);
		}
		
	}



	public Hit search(String word) {
		
		//log.debug("lkey("+lkey+") char is "+first);
		Hit hit=new Hit();
		hit.setSearchWord(word);
		int baseK=0;
		BaseNode hitNode=null;
		for (int k = 0; k < word.length(); k++) {
			
			char cChar=word.charAt(k);
			int ckey=ChineseEncoder.hashCode(cChar);
			
			
			hitNode=baseArray.get(baseK+ckey);
			if(hitNode!=null){
				baseK=hitNode.getValue();
			}
			else{
				hit.setState(Hit.STATE_NONE);
				return hit;
			}
			
			log.debug(hitNode);
			
		}
		
		if(hitNode.getWordItem()!=null){
			hit.setWord(hitNode.getWordItem());
			hit.setState(Hit.STATE_HIT);			
		}
		else{
			hit.setState(Hit.STATE_NEXT);
		}
		return hit;
	}
	
	public void printDATrie(){
		int size=0;
		int i=0;
		int ii=0;
		for (BaseNode bn : baseArray) {			
			if(bn!=null){
				//log.info(i+","+bn+","+checkArray.get(i));
				size++;
				ii=i;
			}
				
			
			i++;
		}
		
		log.info("actual size is "+size+",ii="+ii);
		
		
	}
	
	public static void main(String[] args) {
		DoubleTrieDictionary dtd=new DoubleTrieDictionary(5000000);
		dtd.loadDicFromFile("dic/coreDict.dct");
		dtd.printDATrie();
		
		//dtd.search("阿拉伯埃及共和国");
	}
	


}


