package org.loonglab.segment.dictionary.impl;

import org.loonglab.segment.dictionary.WordItem;

/**
 * 保存词典搜索的结果
 * @author loonglab
 *
 */
public class WordResult {
	
	/**
	 * 查询的词条
	 */
	String searchWord;
	
	/**
	 * 搜索的结果，如果为null，则表示没有搜索到该词
	 */
	WordItem wordItem;
	
	/**
	 * 搜索以该词开头的词时，在wordtable里用二分法搜索时开始的索引值
	 * 如果该值为-1，表示没有以该词开头的词，不需要继续搜索
	 */
	int start;
	
	/**
	 * 搜索以该词开头的词时，在wordtable里用二分法搜索时结束的索引值
	 */
	int end;

	public WordItem getWordItem() {
		return wordItem;
	}

	public void setWordItem(WordItem wordItem) {
		this.wordItem = wordItem;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public WordResult(WordItem wordItem, int start, int end) {
		super();
		this.wordItem = wordItem;
		this.start = start;
		this.end = end;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	
	
	
}
