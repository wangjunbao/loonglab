package org.loonglab.segment.dictionary.loader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loonglab.segment.SegmentException;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.dictionary.trie.TrieTreeDictionary;

public class TxtDicFileLoader {
	
	static Log log = LogFactory.getLog(TxtDicFileLoader.class);
	
	public static TrieTreeDictionary loadDic(String filename){
		List<WordItem> allWords=new ArrayList<WordItem>();
		
		try {
			//1、将词典文件读入数组
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"GBK"));

			String wordStr;
			//int maxWordLength=0;
			WordItem lastWordItem=null;
			while((wordStr=br.readLine())!=null){
				String[] words=wordStr.split(" ");
				if(words.length<3)
					continue;
				//注意，这里要求字典文件是排好序的
				if(lastWordItem!=null&&lastWordItem.getWord().equals(words[0])){					
					lastWordItem.addPos(new Integer(words[2]),new Integer(words[1]));
				}
				else{
					WordItem wi=new WordItem(words[0],new Integer(words[2]),new Integer(words[1]));
					allWords.add(wi);
					lastWordItem=wi;
				}
				
			}
			br.close();

			
			TrieTreeDictionary dic=new TrieTreeDictionary(allWords);
			
			return dic;
			

			
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
			throw new SegmentException(e1.getMessage(),e1);
		}
	}
	
	
}
