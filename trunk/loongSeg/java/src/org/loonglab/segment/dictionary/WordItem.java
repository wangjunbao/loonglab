package org.loonglab.segment.dictionary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.loonglab.segment.util.ChineseEncoder;

/**
 * 词条.包括词内容、长度、句柄及频度
 * 包括不同词性
 * 
 */
public class WordItem implements Comparable<WordItem>,Cloneable{
	
	public static final String SENTENCE_BEGIN = "始##始";

	public static final String SENTENCE_END = "末##末";
	
	public static final String UNKNOWN_PERSON = "未##人";

	public static final String UNKNOWN_SPACE = "未##地";

	public static final String UNKNOWN_NUM = "未##数";

	public static final String UNKNOWN_TIME = "未##时";

	public static final String UNKNOWN_LETTER = "未##串";
	
	private String word;
	//private int handle;// 句柄，用来标识词的词性
	//private int freq;// 频度，用来说明该词出现在语料库中的次数或概率

	private List<POS> posList=new ArrayList<POS>();
	
	/**
	 * 当该词为未登录词时，word属性表示该词的类别，srcWord表示该词原始内容
	 * 因为，在应用n最短路径算法时，是通过未登录词类别来计算同现概率（即两节点路径的权值）
	 */
	private String srcWord;
	
	//Dictionary biDic;//二元词条词典
	
	
	
	public WordItem(String word){
		this.word=word;
	}
	
	public WordItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WordItem(String _word,int _handle,int _freq) {
		word=_word;
		POS pos=new POS(_handle,_freq);
		posList.add(pos);
	}

	public List<POS> getPosList() {
		return posList;
	}

	public void setPosList(List<POS> posList) {
		this.posList = posList;
	}

	public void addPos(int handle,int freq){
		POS pos=new POS(handle,freq);
		posList.add(pos);
	}
	
	public void addPos(POS pos){
		posList.add(pos);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
	public String toString() {

		return ReflectionToStringBuilder.toString(this);
		//return word;

	}
	
	public int getFreq(){
		int freq=0;
		for (POS pos : posList) {
			freq=freq+pos.getFreq();
		}
		
		return freq;
	}



	public int compareTo(WordItem o) {
		String s1=this.getWord();
		String s2=o.getWord();
		
		int len = Math.min(s1.length(), s2.length());
		for (int i = 0; i < len; i++) {
			char c1=s1.charAt(i);
			char c2=s2.charAt(i);
			int key1=ChineseEncoder.hashCode(c1);
			int key2=ChineseEncoder.hashCode(c2);
			if(key1>key2)
				return 2;
			else if(key1<key2)
				return -2;
		}

		if (s1.length() > s2.length())
			return 1;
		else if (s1.length() < s2.length())
			return -1;
		else
			return 0;

	}

	public String getSrcWord() {
		return srcWord;
	}

	public void setSrcWord(String srcWord) {
		this.srcWord = srcWord;
	}

	@Override
	public Object clone() {
		WordItem item;
		try {
			item = (WordItem) super.clone();
			item.setPosList(new ArrayList<POS>());
			for (POS pos : posList) {
				item.addPos((POS)pos.clone());
			}
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		return item;
		
	}

	public POS getPos(){
		for (POS pos : posList) {
			if(pos.isBest())
				return pos;
		}
		
		return null;
	}

	
	
	
	
	 

}
