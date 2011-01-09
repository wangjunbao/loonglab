package org.loonglab.demo;

import java.util.HashMap;
import java.util.Map;

public class W {
	
	private static Map<String, Long> timeMap=new HashMap<String, Long>(); 
	
	//public static long startTime;
	//public static String name;
	public static void start(String name){
		//name=n;
		timeMap.put(name, System.currentTimeMillis());
		//startTime=System.currentTimeMillis();
	}
	
	public static void end(String name){
		System.out.println("'"+name+"' cost time is "+(System.currentTimeMillis()-timeMap.get(name)));
		timeMap.remove(name);
	}
}
