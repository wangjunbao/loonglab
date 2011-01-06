package org.loonglab.demo;

import java.io.File;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

public class LargeScaleDataDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		long startTime=System.currentTimeMillis();
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("D:\\project\\solr1\\index")), true); // only searching, so read-only=true
		
		System.out.println("cost time is "+(System.currentTimeMillis()-startTime));
		reader.close();

	}

}
