package org.ictclas4j.bean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ictclas4j.utility.GFString;
import org.ictclas4j.utility.SegException;
import org.ictclas4j.utility.Utility;

/**
 * 文本形式的字典，不支持从程序里修改字典
 * @author loonglab
 *
 */
public class TxtDictionary extends Dictionary {
	
	static Log log = LogFactory.getLog(TxtDictionary.class);
	
	

	public TxtDictionary() {
		super();		
	}



	public TxtDictionary(String filename) {
		super(filename);		
	}



	@Override
	public boolean load(String filename, boolean isReset) {
		
		try {
			//兼容旧版本
			delModified();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"GBK"));
			for (int i = 0; i < Utility.CC_NUM; i++) {
				String countStr=br.readLine();
				//System.out.println(countStr);
				int count=new Integer(countStr);
				wts.get(i).setCount(count);				
				if(count>0){
					WordItem[] wis = new WordItem[count];
					for (int j = 0; j < count; j++) {
						String wordStr=br.readLine();						
						String[] words=wordStr.split(" ");
						String word=words[0].substring(1);
						WordItem wi=new WordItem(word,word.length()+1,new Integer(words[2]),new Integer(words[1]));
						//System.out.println(wi);
						wis[j]=wi;
					}
					
					wts.get(i).setWords(wis);
				}
			}
			
			br.close();
			
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
			return false;
		}

		return true;
	}
	
	@Override
	public void loadUserDict(String filename){
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"GBK"));
			
			String wordStr;
			while((wordStr=br.readLine())!=null){
				String[] wordArray=wordStr.split(" ");
				String wordHead=wordArray[0].substring(0,1);
				String wordTail=wordArray[0].substring(1);
				int id=Utility.CC_ID(wordHead);
				WordTable wt=wts.get(id);
				if(wt!=null){
					if(wt.getCount()==0){
						wt.setWords(new ArrayList<WordItem>());						
					}
					WordItem wi=new WordItem(wordTail,wordTail.length()+1,new Integer(wordArray[2]),new Integer(wordArray[1]));
					wt.getWords().add(wi);
					
					Collections.sort(wt.getWords(), new Comparator<WordItem>(){

						public int compare(WordItem o1, WordItem o2) {
							return GFString.compareTo(o1.getWord(), o2.getWord());
						}
						
					});
				}
			}
			
			br.close();
			
			
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new SegException(e.getMessage(),e);
		}
		
	}
	
}
