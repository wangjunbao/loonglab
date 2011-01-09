package org.loonglab.demo;

import java.io.File;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

public class LargeScaleDataDemo {
	
	private static String dir1="index";
	private static String dir2="D:\\project\\index_cms\\solr1\\index";
	private static String dir3="D:\\project\\solr1\\index";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//long startTime=System.currentTimeMillis();
		
		W.start("total");
		IndexReader reader = IndexReader.open(FSDirectory.open(new File(dir1)), true); // only searching, so read-only=true
		W.end("total");
		//System.out.println("cost time is "+(System.currentTimeMillis()-startTime));
		reader.close();

	}

}
