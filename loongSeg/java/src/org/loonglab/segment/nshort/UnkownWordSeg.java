package org.loonglab.segment.nshort;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.dictionary.loader.TxtDicFileLoader;
import org.loonglab.segment.dictionary.trie.TrieTreeDictionary;

public class UnkownWordSeg {
	
	static Log log = LogFactory.getLog(UnkownWordSeg.class);
	
	protected TrieTreeDictionary unknownDict;
	
	public static int NL_LETTER=1;//表示字母和连字符
	public static int NL_NUMBER=2;//表示数字
	public static int NL_POINT=3;//表示点
	
	
		
	public UnkownWordSeg(TrieTreeDictionary unknownDict) {
		super();
		this.unknownDict = unknownDict;
	}



	/**
	 * 在粗分的结果中识别出字母、数字和连字符等连在一起的词，组合成一个词
	 * @param firstSegResult 上一步粗分的结果
	 * @return 处理完成后的结果，list的大小应该小于等于输入的list大小
	 */
	List<WordItem> segNumLetter(List<WordItem> firstSegResult){
		
		String tagStr="";
		for (WordItem wordItem : firstSegResult) {
			WordItem item=unknownDict.searchWord(wordItem.getWord());
			if(item!=null)
				tagStr+=(char)(item.getPos().getTag()+'A');
			else
				tagStr+='A';			
		}
		
		log.info(tagStr);
		
		return null;
	}
	
	public static void main(String[] args) {
		TrieTreeDictionary unknownDic=TxtDicFileLoader.loadDic("dic/nl.dct");
		UnkownWordSeg seg=new UnkownWordSeg(unknownDic);
		//seg.segNumLetter(firstSegResult)
	}
	
	
}
