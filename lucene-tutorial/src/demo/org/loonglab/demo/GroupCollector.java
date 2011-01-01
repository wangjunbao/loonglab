package org.loonglab.demo;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.Scorer;

public class GroupCollector extends Collector{
	private GroupField gf=new GroupField();	
	private int docBase;	
	//fieldCache
	private String[] fc;
	public void setFc(String[] fc) {
		this.fc = fc;
	}
	@Override
	public void setScorer(Scorer scorer) throws IOException {
	}	
	@Override
	public void setNextReader(IndexReader reader, int docBase)
			throws IOException {
		this.docBase=docBase;
	}
	
	@Override
	public void collect(int doc) throws IOException {
		int docId=doc+docBase;
		gf.addValue(fc[docId]);
	}
	
	@Override
	public boolean acceptsDocsOutOfOrder() {
		return true;
	}
	
	public GroupField getGroupField(){
		return gf;
	}
}