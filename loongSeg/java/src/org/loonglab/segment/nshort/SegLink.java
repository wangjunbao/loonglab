package org.loonglab.segment.nshort;

/**
 * 词之间的连接
 * @author loonglab
 *
 */
public class SegLink {
	/**
	 * 权值
	 */
	double value;
	
	/**
	 * 指向的目标节点
	 */
	SegNode target;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public SegNode getTarget() {
		return target;
	}

	public void setTarget(SegNode target) {
		this.target = target;
	}

	public SegLink(double value, SegNode target) {
		super();
		this.value = value;
		this.target = target;
	}
	
	
}
