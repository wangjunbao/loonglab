package org.loonglab.demo;

import java.io.File;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiTermQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class MultiTermQueryDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")), true); // only searching, so read-only=true
		
		IndexSearcher searcher=new IndexSearcher(reader);

		MultiTermQuery mtq = new PrefixQuery(new Term("contents", "gr"));
		
//		TopDocs tds=searcher.search(mtq, 10);
		
		
		
		Query q=mtq.rewrite(reader);
		
		System.out.println("q is "+q);
		
		//System.out.println(tds.totalHits);
		
		reader.close();

	}

}
