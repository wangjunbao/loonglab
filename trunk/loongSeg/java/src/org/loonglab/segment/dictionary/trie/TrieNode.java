package org.loonglab.segment.dictionary.trie;

import java.util.List;

import org.loonglab.segment.dictionary.WordItem;

/**
 * Trie树节点，每个节点对应一个字符
 * @author loonglab
 *
 */
public class TrieNode implements Comparable<TrieNode>{
	
	/**
	 * 子节点列表，null表示没有子节点
	 */
	List<TrieNode> subNodes;
	
	/**
	 * 如果该节点为某词语的末节点，则该属性表示对应的词语，否则该属性为null
	 */
	WordItem wordItem;
	
	/**
	 * 该节点对应的字符
	 */
	char ch;
	
	/**
	 * 第几层
	 */
	int layer;
	
	

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public List<TrieNode> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(List<TrieNode> subNodes) {
		this.subNodes = subNodes;
	}

	public WordItem getWordItem() {
		return wordItem;
	}

	public void setWordItem(WordItem wordItem) {
		this.wordItem = wordItem;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public int compareTo(TrieNode o) {
		
		return ch-o.getCh();
	}
	
	
	
}
