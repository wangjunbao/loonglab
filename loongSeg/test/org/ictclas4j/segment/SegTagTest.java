package org.ictclas4j.segment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.utility.Utility;

public class SegTagTest extends TestCase {
	
	static Log log = LogFactory.getLog(SegTagTest.class);

	//SegTag segTag = new SegTag("dic");
	
	public void testSplit() throws Exception {
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("segtest.txt"),"GBK"));
		StringBuffer text=new StringBuffer();
		String line=reader.readLine();
		while(line!=null){
			text.append(line);
			line=reader.readLine();
		}
		reader.close();
		
		//SegResult result=segTag.split(text.toString());
		
		//log.info(result.getFinalResult());
	}
	
	public void testUtility() throws Exception {
		log.info("===="+Utility.CC_ID("/"));
	}

}
