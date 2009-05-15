package org.loonglab.segment.postag;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 词性二元统计
 * @author loonglab
 *
 */
public class TagContext {
	/**
	 * 支持多个二元统计模型，目前无用
	 */
	private int key;

	/**
	 * 词性二元词频统计
	 */
	private int[][] contextArray;

	/**
	 * 单个词性的频次
	 */
	private int[] tagFreq;

	/**
	 * 所有词性的频次总和
	 */
	private int totalFreq;

	public int[][] getContextArray() {
		return contextArray;
	}

	public void setContextArray(int[][] contextArray) {
		this.contextArray = contextArray;
	}

 
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int[] getTagFreq() {
		return tagFreq;
	}

	public void setTagFreq(int[] tagFreq) {
		this.tagFreq = tagFreq;
	}

	public int getTotalFreq() {
		return totalFreq;
	}

	public void setTotalFreq(int totalFreq) {
		this.totalFreq = totalFreq;
	}
	public String toString() {

		return ReflectionToStringBuilder.toString(this);

	}
}
