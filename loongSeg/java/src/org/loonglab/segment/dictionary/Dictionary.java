package org.loonglab.segment.dictionary;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Dictionary {
	
	static Log log = LogFactory.getLog(Dictionary.class);
	
	/**
	 * 首字hash索引，剩余部分用二分法查找
	 */
	public Map<Integer,WordTable> wts;



	public Dictionary(String filename) {
		
	}




}
