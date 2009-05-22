package org.loonglab.segment.dictionary;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 词性标记
 * 
 */
public class POS implements Cloneable{
	private int tag;// 词性标记，如：nr，高8位代表词性的第一个字符，低8位代表词的第二个字符

	private int freq;// 该词性出现的频率
	
	private boolean isBest;
	
	public  POS(){
		
	}
	
	public POS(int pos,int value){
		this.tag=pos;
		this.freq=value;
	}
	
	public int getTag() {
		return tag;
	}

	public void setTag(int pos) {
		this.tag = pos;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int value) {
		this.freq = value;
	}
  
	public boolean isBest() {
		return isBest;
	}

	public void setBest(boolean isBest) {
		this.isBest = isBest;
	}

	public String toString() {

		return ReflectionToStringBuilder.toString(this);

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	
}
