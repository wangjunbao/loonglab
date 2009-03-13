package org.loonglab.segment.dictionary;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * 相同开头的词组表.
 * 
 * 
 */
public class WordTable {


	private ArrayList<WordItem> words;
	
	public WordTable(){
		
	}



	public ArrayList<WordItem> getWords() {
		return words;
	}

	public void setWords(ArrayList<WordItem> words) {
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
