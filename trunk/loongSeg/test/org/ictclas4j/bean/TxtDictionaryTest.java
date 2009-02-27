package org.ictclas4j.bean;

import junit.framework.TestCase;

public class TxtDictionaryTest extends TestCase {

	TxtDictionary td=new TxtDictionary();
	
	public void testLoadStringBoolean() {
		td.load("dic/coreDict.dct");
	}

}
