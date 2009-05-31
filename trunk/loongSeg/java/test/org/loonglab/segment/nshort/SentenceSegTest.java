package org.loonglab.segment.nshort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SentenceSegTest extends TestCase {

	static Log log = LogFactory.getLog(SentenceSegTest.class);
	
	public void testSentenceSeg() throws Exception{
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("test.txt"),"gbk"));
		StringBuffer text=new StringBuffer();
		String line=reader.readLine();
		while(line!=null){
			text.append(line);
			line=reader.readLine();
		}
		reader.close();
		
		SentenceSeg ss=new SentenceSeg(text.toString());
		List<Sentence> sList=ss.getSens();
		
		for (Sentence sentence : sList) {
			log.info(sentence);
		}
	}

}
