package org.loonglab.segment.nshort;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SentenceSegTest extends TestCase {

	static Log log = LogFactory.getLog(SentenceSegTest.class);
	
	public void testSentenceSeg() {
		SentenceSeg ss=new SentenceSeg("日子选的不是你花枝招展的时候，也不是你风姿绰约时候，而是酷暑难当、烈日当空、你素面朝天的日子。");
		List<Sentence> sList=ss.getSens();
		
		for (Sentence sentence : sList) {
			log.info(sentence);
		}
	}

}
