package org.loonglab.segment.dictionary.datrie;

import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.util.ChineseEncoder;

public class BaseNode {
	int value;
	
	char nodeChar;
	
	WordItem wordItem=null;


	public WordItem getWordItem() {
		return wordItem;
	}

	public void setWordItem(WordItem wordItem) {
		this.wordItem = wordItem;
	}

	public BaseNode(int value, char c) {
		super();
		this.value = value;
		this.nodeChar = c;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public char getNodeChar() {
		return nodeChar;
	}

	public void setNodeChar(char nodeChar) {
		this.nodeChar = nodeChar;
	}

	@Override
	public String toString() {
		return "BaseCode("+value+","+nodeChar+"("+ChineseEncoder.hashCode(nodeChar)+")),"+wordItem;
	}
	
	
	
	
	
}
