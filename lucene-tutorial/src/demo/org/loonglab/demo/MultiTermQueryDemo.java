package org.loonglab.demo;

import java.io.File;
import java.util.Iterator;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.ConstantScoreQuery;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiTermQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class MultiTermQueryDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")), true); // only searching, so read-only=true
		
		
		TermEnum te=reader.terms(new Term("iphone",""));
		
		
		while(te.next()){
			System.out.println(te.term().text()+","+te.term().field()+","+te.docFreq());
		}
		
		IndexSearcher searcher=new IndexSearcher(reader);

//		MultiTermQuery mtq = new PrefixQuery(new Term("contents", "gr"));
//		
////		TopDocs tds=searcher.search(mtq, 10);
//		
//		
//		
//		Query q=mtq.rewrite(reader);
//		
//		if(q instanceof ConstantScoreQuery){
//			ConstantScoreQuery csq=(ConstantScoreQuery)q;
//			DocIdSet dis=csq.getFilter().getDocIdSet(reader);
//			for (DocIdSetIterator iterator = dis.iterator(); DocIdSetIterator.NO_MORE_DOCS!=iterator.nextDoc();) {
//				int docid = iterator.docID();
//				System.out.println(docid);
//				
//			}
//		}
//		
//		System.out.println("q is "+q.getClass().getCanonicalName());
		
		//System.out.println(tds.totalHits);
		
		//Query q=QueryParser.
		
		reader.close();

	}

}
