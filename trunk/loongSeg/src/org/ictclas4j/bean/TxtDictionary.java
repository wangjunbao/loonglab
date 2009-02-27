package org.ictclas4j.bean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
}
