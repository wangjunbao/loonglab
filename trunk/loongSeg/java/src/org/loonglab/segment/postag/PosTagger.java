package org.loonglab.segment.postag;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.dictionary.trie.TrieTreeDictionary;

public class PosTagger {

	static Log log = LogFactory.getLog(PosTagger.class);

	protected TrieTreeDictionary coreDict;
	protected TrieTreeDictionary unknownDict;
	protected ContextStat context;
	
//	public static final String SENTENCE_BEGIN = "始##始";
//
//	public static final String SENTENCE_END = "末##末";

	public PosTagger(TrieTreeDictionary coreDic, TrieTreeDictionary unknownDic,
			ContextStat context) {
		this.coreDict = coreDic;
		this.unknownDict = unknownDic;
		this.context = context;
	}

	/**
	 * 对N最短路径粗分结果进行标注，用于识别未登录词
	 * 
	 * @param firstSegResult
	 * @return
	 */
	public void posUnkownTag(List<WordItem> firstSegResult){
		
		//需要创建TagNode类，存放中间结果，用于viterbi算法词性标注
		List<WordItem> tagResult=new ArrayList<WordItem>();
		
		//对每个词进行标注
		for (WordItem wordItem : firstSegResult) {
			WordItem tagItem=unknownDict.searchWord(wordItem.getWord());

			if(tagItem!=null){
				tagItem.addPos(0, wordItem.getFreq());
			}
			else{
				tagItem=new WordItem(wordItem.getWord(),0,wordItem.getFreq());
			}

			tagResult.add(tagItem);	
		}
		
		//vertibi算法得到词性序列
		
	}

}
