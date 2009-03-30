package org.loonglab.segment.dictionary.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.loonglab.segment.dictionary.WordItem;


/**
 * 相同开头的词组表.
 * 
 * 
 */
public class WordTable {


	private List<WordItem> words;
	
	public WordTable(){
		
	}



	public List<WordItem> getWords() {
		return words;
	}

	public void setWords(List<WordItem> words) {
		this.words = words;
	}
	
	public void setWords(WordItem[] wis){
		if(wis!=null){
			if(words==null)
				words=new ArrayList<WordItem>();
			for(WordItem wi:wis) 
				words.add(wi);
		 
		}
	}

	public String toString() {

		return ReflectionToStringBuilder.toString(this);

	}
}
