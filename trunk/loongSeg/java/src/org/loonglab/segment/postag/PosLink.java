package org.loonglab.segment.postag;

import org.loonglab.segment.dictionary.POS;

/**
 * 用于viterbi算法词性标注
 * @author loonglab
 *
 */
public class PosLink {
	
	POS pos;
	/**
	 * 前一个词的N个词性中和该词性最匹配的那一个（下标位置）
	 */
	int prev;
	
	/**
	 * 与前一个词的n个此项中的最匹配的那一个的同现概率
	 */
	double value;

	public POS getPos() {
		return pos;
	}

	public void setPos(POS pos) {
		this.pos = pos;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public PosLink(POS pos, double value) {
		super();
		this.pos = pos;
		this.value = value;
	}

	public PosLink() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
