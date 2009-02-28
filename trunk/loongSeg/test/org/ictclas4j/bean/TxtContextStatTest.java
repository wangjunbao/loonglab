package org.ictclas4j.bean;

import junit.framework.TestCase;

public class TxtContextStatTest extends TestCase {
	
	TxtContextStat tcs=new TxtContextStat();

	public void testLoadStringBoolean() {
		tcs.load("dic/lexical.ctx");
	}

}
