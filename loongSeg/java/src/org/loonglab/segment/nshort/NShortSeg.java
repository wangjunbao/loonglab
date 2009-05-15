package org.loonglab.segment.nshort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ictclas4j.utility.Utility;
import org.loonglab.segment.POSTag;
import org.loonglab.segment.SegmentException;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.dictionary.loader.TxtDicFileLoader;
import org.loonglab.segment.dictionary.trie.TrieTreeDictionary;
import org.loonglab.segment.postag.PosTagger;

import com.sun.jmx.remote.internal.ArrayQueue;

public class NShortSeg {
	
	static Log log = LogFactory.getLog(NShortSeg.class);
	
	public static WordItem SENTENCE_BEGIN=new WordItem("始##始",1,50610);
	public static WordItem SENTENCE_END=new WordItem("末##末",4,50610);
	
	public static int MAX_FREQUENCE=2079997;
	public static double smoothParam = 0.1;
	
	TrieTreeDictionary dic;
	
	/**
	 * 二元统计词典
	 */
	TrieTreeDictionary biDic;
	
	
	
	public NShortSeg(TrieTreeDictionary dic,TrieTreeDictionary biDic) {
		this.dic=dic;
		this.biDic=biDic;
	}



	public List<WordItem> segSentence(String sentence){
		//1、构造词语网络图
		
		//先构造初始节点
		SegNode firstNode=new SegNode();
		firstNode.setPathValue(0);
		firstNode.setWordItem(SENTENCE_BEGIN);
		firstNode.setNextIndex(0);
		
		SegNode lastNode=new SegNode();
		lastNode.setPathValue(Double.MAX_VALUE);
		lastNode.setWordItem(SENTENCE_END);
		lastNode.setNextIndex(sentence.length());
		
		//开始切分句子
		Queue<SegNode> nodeQueue=new LinkedList<SegNode>();
		nodeQueue.add(firstNode);
		
		while(!nodeQueue.isEmpty()){
			SegNode parentNode=nodeQueue.poll();
			if(parentNode.getNextIndex()==sentence.length()){
				parentNode.addLink(new SegLink(0,lastNode));
				
			}
			else{
				String subSen=sentence.substring(parentNode.getNextIndex());
				
				List<WordItem> results=dic.allSplit(subSen);
				
				for (WordItem wordItem : results) {
					SegNode node=new SegNode();
					node.setPathValue(Double.MAX_VALUE);
					node.setWordItem(wordItem);
					node.setNextIndex(parentNode.getNextIndex()+wordItem.getWord().length());
					parentNode.addLink(new SegLink(0,node));
					
					nodeQueue.add(node);
				}
			}
			
			
//			if(results.size()==0){
//				String wordStr=sentence.substring(i,i+1);
//				SegNode node=new SegNode();
//				node.setPathValue(Double.MAX_VALUE);
//				node.setWordItem(new WordItem(wordStr,POSTag.UNKNOWN,0));
//				
//				sn.addLink(new SegLink(0,node));
//			}
		}
		

		
		//更新各条边的权值
		
		calcLinkValue(firstNode);
		print(firstNode);
		
		//2、dijkstra算法
		List<SegNode> open=new ArrayList<SegNode>();
		open.add(firstNode);
		
		
		
		SegNode sn=null;
		while(sn!=lastNode&&!open.isEmpty()){
			sn=open.get(0);
			
			open.remove(sn);
			
			List<SegLink> links=sn.getLinks();
			for (SegLink segLink : links) {
				if(segLink.getTarget().getPathValue()>segLink.getValue()){
					segLink.getTarget().setPathValue(segLink.getValue());
					segLink.getTarget().setPathParent(sn);
				}
					
				
				if(!open.contains(segLink.getTarget())){
					open.add(segLink.getTarget());
				}
			}
			
			
			//排序
			Collections.sort(open,new Comparator<SegNode>(){

				public int compare(SegNode o1, SegNode o2) {
					if(o1.getPathValue()>o2.getPathValue())
						return 1;
					else if(o1.getPathValue()<o2.getPathValue())
						return -1;
					else
						return 0;
				}
				
			});
		}
		
		List<WordItem> path=new ArrayList<WordItem>();
		if(sn==lastNode){
			path.add(sn.getWordItem());
			while(sn.getPathParent()!=null){
				path.add(sn.getPathParent().getWordItem());
				sn=sn.getPathParent();
			}
				
		}
		
		Collections.reverse(path);
		
		
		return path;
	}
	
	private void calcLinkValue(SegNode firstNode) {
		List<SegLink> links=firstNode.getLinks();
		
		String wordStr=firstNode.getWordItem().getWord()+"@";
		for (SegLink segLink : links) {
			String word=wordStr+segLink.getTarget().getWordItem().getWord();
			WordItem wi=biDic.searchWord(word);
			//词典中存在两词之间的频率统计
			double temp = (double) 1 / MAX_FREQUENCE;
			//curFreq究竟是计算前一个词还是后一个词的词频
			int curFreq=firstNode.getWordItem().getFreq();
			int twoFreq=0;
			if(wi!=null){
				twoFreq=wi.getFreq();
			}
			
			double value = smoothParam * (1 + curFreq) / (MAX_FREQUENCE + 80000);
			value += (1 - smoothParam) * ((1 - temp) * twoFreq / (1 + curFreq) + temp);
			value = -Math.log(value);

			segLink.setValue(value);
			
			calcLinkValue(segLink.getTarget());
		}
		
	}



	//递归方法打印
	private void print(SegNode firstNode) {
		//log.info(firstNode);
		String parentStr=firstNode.getWordItem().getWord();
		for (SegLink link : firstNode.getLinks()) {
			log.info("["+parentStr+"]("+link.getTarget().getWordItem().getWord()+","+link.getValue()+")");
			print(link.getTarget());
		}
		
	}



	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		TrieTreeDictionary dic=TxtDicFileLoader.loadDic("dic/coreDict.dct");
		TrieTreeDictionary biDic=TxtDicFileLoader.loadDic("dic/bigramDict.dct");
		NShortSeg nsg=new NShortSeg(dic,biDic);
		log.info("loading cost is "+(System.currentTimeMillis()-startTime));
		List<WordItem> result=nsg.segSentence("刘岗说的确实有理");		
		
		log.info("total cost is "+(System.currentTimeMillis()-startTime));
		
		for (WordItem wordItem : result) {
			System.out.print(wordItem.getWord()+"/");
		}
		
		TrieTreeDictionary unknownDic=TxtDicFileLoader.loadDic("dic/nr.dct");
		
		
		PosTagger posTagger=new PosTagger(dic,unknownDic,null);
		
		posTagger.posUnkownTag(result);

	}
}
