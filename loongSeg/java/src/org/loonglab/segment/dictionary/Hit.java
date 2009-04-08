package org.loonglab.segment.dictionary;

/**
 * 查找的结果
 * @author loonglab
 *
 */
public class Hit {
	
	/**
	 * 查询的词串
	 */
	String searchWord;
	
	/**
	 * 查找到的单词，若没有查找到，则为null
	 */
	WordItem word;
	
	/**
	 * 表示查询结果的状态
	 */
	int state;
	
	/**
	 * 没有找到
	 */
	public static int STATE_NONE=-1;
	/**
	 * 没找到，但存在前面字符相同的单词
	 */
	public static int STATE_NEXT=0;
	/**
	 * 找到该单词
	 */
	public static int STATE_HIT=1;
	
	
	
	
	public Hit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hit(String searchWord, WordItem word, int state) {
		super();
		this.searchWord = searchWord;
		this.word = word;
		this.state = state;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public WordItem getWord() {
		return word;
	}

	public void setWord(WordItem word) {
		this.word = word;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
	
}
