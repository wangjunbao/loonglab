package org.ictclas4j.bean;

import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 词条.包括词内容、长度、句柄及频度
 * 
 * @author sinboy
 * 
 */
public class WordItem {
	private String word;
	private int len;//词在磁盘上存储的字节长度
	private int handle;// 句柄，用来标识词的词性
	private int freq;// 频度，用来说明该词出现在语料库中的次数或概率
	
	private int index;//在wordTable里的索引号
	
	/**
	 * 使用jdk自带的HashMap实现，因为数据量较小，应该能保证性能
	 * 若性能无法满足，可考虑两层索引
	 */
	private Map<String,BiWordItem> biDic;//以该wordItem为开头的二元词条列表
	
	


	public Map<String, BiWordItem> getBiDic() {
		return biDic;
	}

	public void setBiDic(Map<String, BiWordItem> biDic) {
		this.biDic = biDic;
	}

	WordItem() {
		
	}
	
	WordItem(String _word,int _len,int _handle,int _freq) {
		word=_word;
		len=_len;
		handle=_handle;
		freq=_freq;
	}
	
	WordItem(String _word,int _len,int _handle,int _freq,int _index) {
		word=_word;
		len=_len;
		handle=_handle;
		freq=_freq;
		index=_index;
	}
	
	
	public int getFreq() {
		return freq;
	}

	public void setFreq(int frequency) {
		this.freq = frequency;
	}

	public int getHandle() {
		return handle;
	}

	public void setHandle(int handle) {
		this.handle = handle;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
	public String toString() {

		return ReflectionToStringBuilder.toString(this);

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	 

}
