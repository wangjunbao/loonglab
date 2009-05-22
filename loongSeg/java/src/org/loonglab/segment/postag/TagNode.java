package org.loonglab.segment.postag;

import java.util.ArrayList;
import java.util.List;

import org.loonglab.segment.dictionary.WordItem;

public class TagNode {
	private WordItem wordItem;	

	private List<PosLink> links=new ArrayList<PosLink>();
	
	public WordItem getWordItem() {
		return wordItem;
	}
	public void setWordItem(WordItem wordItem) {
		this.wordItem = wordItem;
	}
	public List<PosLink> getLinks() {
		return links;
	}
	public void setLinks(List<PosLink> links) {
		this.links = links;
	}
	@Override
	public String toString() {		
		int bestTag=-1;
		
		for (PosLink link : links) {
			if(link.getPos().isBest()){
				bestTag=link.getPos().getTag();
				break;
			}
				
		}
		
		return wordItem.getWord()+"/"+bestTag;
	}

	
	
	
	
}
