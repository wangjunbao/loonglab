package org.loonglab.demo;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class FieldCacheDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")), true); // only searching, so read-only=true

		String[] fc=FieldCache.DEFAULT.getStrings(reader, "modified");
		
		Searcher searcher = new IndexSearcher(reader);
	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);

		
		QueryParser parser=new QueryParser(Version.LUCENE_30, "content", analyzer);
		Query query=parser.parse("*:*");
		
		
		TopDocs docs=searcher.search(query, 100);
		
		for (int i = 0; i < docs.scoreDocs.length; i++) {
			ScoreDoc doc=docs.scoreDocs[i];
			System.out.println(doc.doc);
		}
		
		System.out.println("fc.length="+fc.length+",maxDoc="+reader.maxDoc());
		
		for (int i = 0; i < fc.length; i++) {
			System.out.println("fc["+i+"]="+fc[i]);
		}
		
		Document doc=reader.document(3);
		System.out.println("doc[3].path="+doc.get("path"));

	}

}
