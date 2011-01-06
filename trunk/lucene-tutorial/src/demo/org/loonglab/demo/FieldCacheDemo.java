package org.loonglab.demo;

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class FieldCacheDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")), true); // only searching, so read-only=true

		//读取"modified"字段值，放到fieldCache中
		final String[] fc=FieldCache.DEFAULT.getStrings(reader, "modified");
		
		Searcher searcher = new IndexSearcher(reader);
//	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);		
//		QueryParser parser=new QueryParser(Version.LUCENE_30, "content", analyzer);
//		Query query=parser.parse("*:*");
		
		//自定义文档收集器，用于实现分组统计
		GroupCollector myCollector=new GroupCollector();
		myCollector.setFc(fc);
		
		searcher.search(new MatchAllDocsQuery(), myCollector);
		
		//GroupField用来保存分组统计的结果
		GroupField gf=myCollector.getGroupField();		
		List<String> values=gf.getValues();		
		for (String value : values) {
			System.out.println(value+"="+gf.getCountMap().get(value));
		}

		reader.close();
	}

}
