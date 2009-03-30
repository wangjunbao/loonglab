package org.loonglab.segment.dictionary.impl;

public class BiWordItem {	
	String word;//为二元关系中的第二个词
	int freq;
	int handle;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public int getHandle() {
		return handle;
	}
	public void setHandle(int handle) {
		this.handle = handle;
	}
	
	
}
