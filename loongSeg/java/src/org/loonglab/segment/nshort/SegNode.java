package org.loonglab.segment.nshort;

import java.util.ArrayList;
import java.util.List;

import org.loonglab.segment.dictionary.WordItem;

public class SegNode {
	
	/**
	 * 记录最小权值
	 */
	double pathValue;
	
	/**
	 * 记录对应最小权值的父节点
	 */
	SegNode pathParent;
	
	/**
	 * 该节点的子节点分词的开始位置
	 */
	int nextIndex;
	
	/**
	 * 关联的词语
	 */
	WordItem wordItem;
	
	/**
	 * 指向的下一个节点
	 */
	List<SegLink> links=new ArrayList<SegLink>();
	
	
	

	public int getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public WordItem getWordItem() {
		return wordItem;
	}

	public void setWordItem(WordItem wordItem) {
		this.wordItem = wordItem;
	}

	public List<SegLink> getLinks() {
		return links;
	}

	public void setLinks(List<SegLink> links) {
		this.links = links;
	}
	
	public void addLink(SegLink link){
		links.add(link);
	}

	public double getPathValue() {
		return pathValue;
	}

	public void setPathValue(double pathValue) {
		this.pathValue = pathValue;
	}

	public SegNode getPathParent() {
		return pathParent;
	}

	public void setPathParent(SegNode pathParent) {
		this.pathParent = pathParent;
	}
	
	@Override
	public String toString() {
		return wordItem.getWord();
	}
	
	
}
