package org.loonglab.segment.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loonglab.segment.util.ChineseEncoder;

public class CommonTest extends TestCase{
	
	static Log log = LogFactory.getLog(CommonTest.class);
	
	public void testEncode() throws Exception {
//		String ss="1";
//		
//		
//		
//		byte[] gbk=ss.getBytes("gb2312");
//		
//		log.info((int)'1');
////		
////		byte[] gb2312=ss.getBytes("gb2312");
////		
//		log.info("gbk length is "+gbk.length);
////		log.info("gb2312 length is "+gb2312.length);
//		
//		
//	//	log.info(ChineseEncoder.hashGbk(ss));
////		log.info(ChineseEncoder.hashGb2312(ss));
//		log.info(ChineseEncoder.hashCode(ss.charAt(0)));
//		
//		int x1=0xF7FE;
//		int x2=0xB0A1;
		
		log.info(ChineseEncoder.hashGb2312("¡¡"));
		
	}
}
