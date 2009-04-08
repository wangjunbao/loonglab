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
public class WordItem implements Comparable<WordItem>{
	private String word;
	//private int handle;// 句柄，用来标识词的词性
	//private int freq;// 频度，用来说明该词出现在语料库中的次数或概率

	private List<POS> posList=new ArrayList<POS>();
	
	//Dictionary biDic;//二元词条词典
	
	
	public WordItem(String _word,int _handle,int _freq) {
		word=_word;
		POS pos=new POS(_handle,_freq);
		posList.add(pos);
	}

	public void addPos(int handle,int freq){
		POS pos=new POS(handle,freq);
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


	
	
	 

}
