package org.loonglab.segment.nshort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loonglab.segment.SegmentException;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.dictionary.loader.TxtDicFileLoader;
import org.loonglab.segment.dictionary.trie.TrieTreeDictionary;
import org.loonglab.segment.postag.ContextStat;
import org.loonglab.segment.postag.POSTag;
import org.loonglab.segment.postag.PosLink;
import org.loonglab.segment.postag.PosTagger;
import org.loonglab.segment.postag.TagNode;

public class NShortSeg {
	
	static Log log = LogFactory.getLog(NShortSeg.class);
	
	public static WordItem SENTENCE_BEGIN=new WordItem(WordItem.SENTENCE_BEGIN,1,50610);
	public static WordItem SENTENCE_END=new WordItem(WordItem.SENTENCE_END,4,50610);
	
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
		List<WordItem> path = getPath(firstNode);
		
		
		return path;
	}



	private List<WordItem> getPath(SegNode firstNode) {
		List<SegNode> open=new ArrayList<SegNode>();
		open.add(firstNode);
		
		SegNode sn=open.get(0);
		
		
		while(!sn.getWordItem().getWord().equals(WordItem.SENTENCE_END)&&!open.isEmpty()){
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

		path.add(sn.getWordItem());
		while(sn.getPathParent()!=null){
			path.add(sn.getPathParent().getWordItem());
			sn=sn.getPathParent();
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
	
	public List<WordItem> segUnkownWord(List<TagNode> tagNodes){
		String patternStr=word2pattern(tagNodes);
		
		log.debug(patternStr);
		
		// 人名识别模式
		final String[] patterns = { "BBCD", "BBC", "BBE", "BBZ", "BCD", "BEE", "BE", "BG", "BXD", "BZ", "CDCD", "CD",
				"EE", "FB", "Y", "XD"};
		
		//构造识别前的词语网络图
		SegNode parentNode=null;
		List<SegNode> segNodes=new ArrayList<SegNode>();
		for (TagNode tagNode : tagNodes) {
			SegNode segNode=new SegNode();
			segNode.setWordItem(tagNode.getWordItem());
			if(parentNode!=null){
				parentNode.addLink(new SegLink(0,segNode));
				segNode.setPathValue(Double.MAX_VALUE);
			}
				
			
			parentNode=segNode;
			
			segNodes.add(segNode);
		}
		
		//修改词语网络图，增加未登录词
		WordItem personItem=dic.searchWord(WordItem.UNKNOWN_PERSON);
		personItem.getPosList().get(0).setTag(POSTag.NOUN_PERSON);	
		for (String pattern : patterns) {
			int k=-pattern.length();
			while((k=patternStr.indexOf(pattern,k+pattern.length()))!=-1){
				SegNode segNode=new SegNode();
				segNode.setPathValue(Double.MAX_VALUE);
				String srcWord="";
				segNode.setWordItem(personItem);
				segNode.addLink(new SegLink(0,segNodes.get(k+pattern.length())));
				segNodes.get(k-1).addLink(new SegLink(0,segNode));
				
				//拼出实际人名 
				//TODO 把非人名组成成分去除掉
				for (int i = k; i < k+pattern.length(); i++) {
					srcWord=srcWord+segNodes.get(i).getWordItem().getWord();
				}
				
				segNode.getWordItem().setSrcWord(srcWord);
			}
		}
		
		
		calcLinkValue(segNodes.get(0));
		List<WordItem> results=getPath(segNodes.get(0));
		
		return results;
	}


	// 把经过初次分词后的链表形式转成人名字符串模式
	private String word2pattern(List<TagNode> tns) {
		String result = null;

		result = "";
		for (TagNode tn : tns) {
			result += (char) (getBestTag(tn) + 'A');
		}

		return result;
	}

	private int getBestTag(TagNode tn) {
		for (PosLink link : tn.getLinks()) {
			if(link.getPos().isBest())
				return link.getPos().getTag();
		}
		
		return -1;
	}

	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		TrieTreeDictionary dic=TxtDicFileLoader.loadDic("dic/coreDict.dct");
		TrieTreeDictionary biDic=TxtDicFileLoader.loadDic("dic/bigramDict.dct");
		NShortSeg nsg=new NShortSeg(dic,biDic);
		log.info("loading cost is "+(System.currentTimeMillis()-startTime));
		List<WordItem> result=nsg.segSentence("曾佳对刘岗说的确实有理");		
		
		log.info("total cost is "+(System.currentTimeMillis()-startTime));
		
		for (WordItem wordItem : result) {
			System.out.print(wordItem.getWord()+"/");
		}
		
		TrieTreeDictionary unknownDic=TxtDicFileLoader.loadDic("dic/nr.dct");
		ContextStat cs=new ContextStat();
		cs.load("dic/nr.ctx");
		
		ContextStat ncs=new ContextStat();
		ncs.load("dic/lexical.ctx");
		
		
		PosTagger posTagger=new PosTagger(dic,unknownDic,cs);
		
		PosTagger lexPosTagger=new PosTagger(dic,null,ncs);
		
//		List<TagNode> tagNodes=posTagger.posUnkownTag(result);
//		List<WordItem> secResult=nsg.segUnkownWord(tagNodes);
//		
//		System.out.println("");
//		for (WordItem wordItem : secResult) {
//			String word=wordItem.getWord();
//			if(wordItem.getSrcWord()!=null)
//				word=wordItem.getSrcWord();
//			System.out.print(word+"/");
//		}
		
		List<WordItem> finalResult=lexPosTagger.posTag(result);
		
		log.info(outputResult(finalResult));

	}
	
	// 根据分词路径生成分词结果
	private static String outputResult(List<WordItem> wrList) {
		String result = null;
		String temp=null;
		char[] pos = new char[2];
		if (wrList != null && wrList.size() > 0) {
			result = "";
			for (int i = 0; i < wrList.size(); i++) {
				WordItem wi = wrList.get(i);
				if (wi.getPos().getTag() != POSTag.SEN_BEGIN && wi.getPos().getTag() != POSTag.SEN_END) {
					int tag = wi.getPos().getTag();
					pos[0] = (char) (tag / 256);
					pos[1] = (char) (tag % 256);
					temp=""+pos[0];
					if(pos[1]>0)
						temp+=""+pos[1];
					String word=wi.getWord();
					if(wi.getSrcWord()!=null)
						word=wi.getSrcWord();
					result += word + "/" + temp + " ";
				}
			}
		}

		return result;
	}
}
