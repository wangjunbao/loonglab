package org.loonglab.demo;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.FSDirectory;

public class FieldCacheDemo {
	
	private static Instrumentation inst;
	
	public static void premain(String options, Instrumentation inst) {
		FieldCacheDemo.inst = inst;

	      System.out.println("options= " + options);

	      // Get all classes currently loaded by VM
	      Class[] loaded = inst.getAllLoadedClasses();

	      // Sort them by name
	      Arrays.sort(loaded, new Comparator<Class>() {

			@Override
			public int compare(Class c1, Class c2) {
	            return c1.getName().compareTo(c2.getName());
	         }



	      });

	      //And print them!
	      for (Class clazz : loaded) {
	         System.out.println(clazz);
	      }
	   }

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		//System.gc();
		
		
		IndexReader reader = IndexReader.open(FSDirectory.open(new File(C.dir2)), true); // only searching, so read-only=true
		
		W.start("fieldCache");

		SizeOf.runGC();
		long heap1=SizeOf.usedMemory();
		
		//读取"modified"字段值，放到fieldCache中
		final String[] fc=FieldCache.DEFAULT.getStrings(reader, "pubDate_d");
		
		SizeOf.runGC();
		long heap2=SizeOf.usedMemory();
		
		W.end("fieldCache");
		
		System.out.println(fc[0]);
//		
//		String ff="";
//		String hh="h23343434343434343434343434343434343434343434343434343434";
		
		long size=0;
		
		int nullSize=0;
	
		for (String ss : fc) {
			if(ss!=null)
				size=size+ss.length();
			else
				nullSize++;
			
		}
		
		
		
		System.out.println("mysize is "+size+",nullsize is "+nullSize);
		System.out.println(fc.length+",size is "+inst.getObjectSize(fc));
		
		System.out.println("mysizeof result is "+(heap2-heap1));
		

		
		
		Searcher searcher = new IndexSearcher(reader);
//	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);		
//		QueryParser parser=new QueryParser(Version.LUCENE_30, "content", analyzer);
//		Query query=parser.parse("*:*");
		
		//自定义文档收集器，用于实现分组统计
//		GroupCollector myCollector=new GroupCollector();
//		myCollector.setFc(fc);
//		
//		searcher.search(new MatchAllDocsQuery(), myCollector);
//		
//		//GroupField用来保存分组统计的结果
//		GroupField gf=myCollector.getGroupField();		
//		List<String> values=gf.getValues();		
//		for (String value : values) {
//			System.out.println(value+"="+gf.getCountMap().get(value));
//		}

		reader.close();
	}

}
