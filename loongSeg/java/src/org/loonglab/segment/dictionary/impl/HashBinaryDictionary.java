package org.loonglab.segment.dictionary.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ictclas4j.utility.Utility;
import org.loonglab.segment.dictionary.Dictionary;
import org.loonglab.segment.dictionary.Hit;
import org.loonglab.segment.dictionary.WordItem;


public class HashBinaryDictionary implements Dictionary{
	
	static Log log = LogFactory.getLog(HashBinaryDictionary.class);
	
	/**
	 * 首字hash索引，剩余部分用二分法查找
	 */
	public Map<Integer,List<WordItem>> wts=new HashMap<Integer, List<WordItem>>();
	
	private Hit lastHit=null;
	private int lastStart=-1;
	private int lastEnd=-1;



	/**
	 * 读取词典文件
	 * @param filename
	 */
	public HashBinaryDictionary(String filename) {
		loadDic(filename);
	}
	
	public void loadDic(String filename){
		try {
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"GBK"));

			String wordStr;
			while((wordStr=br.readLine())!=null){
				String[] words=wordStr.split(" ");
				String word=words[0].substring(1);
				
				//计算key
				//log.debug("===word is "+word);
				int key=new Integer(words[0].charAt(0));
				
				WordItem wi=new WordItem(word,new Integer(words[2]),new Integer(words[1]));
				List<WordItem> wis=wts.get(key);
				if(wis==null){
					wis=new ArrayList<WordItem>();
					wts.put(key, wis);
				}
				wis.add(wi);
			}
					
			

			
			br.close();
			
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
			throw new RuntimeException(e1.getMessage(),e1);
		}

	}

	public void loadBidDic(String filename){
		
	}
	


	
	
	/**
	 * 比较两个字符串的大小
	 * @param s1
	 * @param s2
	 * @return 0表示两个字符串相等，1表示s1大于s2且两者前面字符相同，仅长度不同，
	 *         2表示s1大于s2，且两者前面字符不同，-1表示s2>s1且两者前面字符相同，仅长度不同
	 *         -2表示s2>s1且前面字符不同
	 */
	public static int compareTo(String s1, String s2) {
		int len = Math.min(s1.length(), s2.length());
		for (int i = 0; i < len; i++) {
			char c1=s1.charAt(i);
			char c2=s2.charAt(i);
			if(c1>c2)
				return 2;
			else if(c1<c2)
				return -2;
		}

		if (s1.length() > s2.length())
			return 1;
		else if (s1.length() < s2.length())
			return -1;
		else
			return 0;


	}

	/**
	 * 在字典里查找词条
	 * @param word 词条
	 * @param start 二分法查找开始位置，如果start=-1,则从0开始查找
	 * @param end 二分法查找的结束位置，如果end=-1，则从最后开始查找
	 * @return
	 */
	public Hit search(String word) {
		if (word != null) {
			String res=word.substring(1);
			int index=keyOf(word.charAt(0));
			if (res != null && wts != null) {
				List<WordItem> wt = wts.get(index);
				if (wt != null && wt.size() > 0) {
					int start=0;
					int end = wt.size() - 1;
					if(lastStart!=-1)
						start = lastStart;
					if(lastEnd!=-1)
						end= lastEnd;
					int mid = 0;
					List<WordItem> wis = wt;
					
					//保存下次查找的范围
					lastStart=start;
					lastEnd=end;
					while (start <= end) {
						mid = (start + end) / 2;
						WordItem wi = wis.get(mid);
						int cmpValue = compareTo(wi.getWord(), res);
						if (cmpValue == 0 ) {
							lastHit=new Hit(word,wi,Hit.STATE_HIT);
							//WordResult wr=new WordResult(wi,resultStart,resultEnd);
							return lastHit;

						} else if (cmpValue < 0 ){						
							start = mid + 1;
							lastStart=start;
						}
							
						else if (cmpValue > 0 ){						
							end = mid - 1;
							if(cmpValue==2)
								lastEnd=end;
						}
							

						
					}
					

					if(wis.get(mid).getWord().startsWith(res)){
						lastHit=new Hit(word,null,Hit.STATE_NEXT);
						return lastHit;
					}

					
				}
				
				
			}
		}
		
		lastHit=new Hit(word,null,Hit.STATE_NONE);
		return lastHit;

	}

    private int keyOf(char c){
    	return new Integer(c);
    }
    
    
    public static void main(String[] args) {
		Dictionary dic=new HashBinaryDictionary("dic/coreDict.dct");
		
		Hit hit=dic.search("阿谀奉承");
		
		
	}

}
