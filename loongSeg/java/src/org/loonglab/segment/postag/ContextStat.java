package org.loonglab.segment.postag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ContextStat {
	
	static Log log = LogFactory.getLog(ContextStat.class);
	
	protected int tableLen;

	protected int[] symbolTable;

	protected TagContext tc;



	public ContextStat() { 
		tc = new TagContext();
	}


	public boolean load(String fileName) {
		try {
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK"));
			
			//词性的个数
			tableLen=new Integer(br.readLine());
			
			//词性列表，共15个
			symbolTable = new int[tableLen];
			String symbolStr=br.readLine();
			String[] symbolArray=symbolStr.split(" ");
			for (int j = 0; j < symbolArray.length; j++) {
				String sym = symbolArray[j];
				symbolTable[j]=new Integer(sym);
			}
			
			//总频次
			//TagContext tc = new TagContext();
			int key=new Integer(br.readLine());
			int totalFreq =new Integer(br.readLine());
			
			tc.setKey(key);
			tc.setTotalFreq(totalFreq);
			
			//读每个词性出现的总频次
			String freqStr=br.readLine();
			String[] freqArray=freqStr.split(" ");
			int[] tagFreq = new int[tableLen];
			for (int i = 0; i < tagFreq.length; i++) {
				tagFreq[i]=new Integer(freqArray[i]);
			}
			
						
			// 读取词性的二元统计矩阵
			int[][] contextArray = new int[tableLen][tableLen];
			for (int i = 0; i < tableLen; i++) {
				String contextStr=br.readLine();
				//System.out.println(contextStr);
				String[] contextStrArray=contextStr.split(" ");
				for (int j = 0; j < contextStrArray.length; j++) {
					contextArray[i][j]=new Integer(contextStrArray[j]);
					//log.debug(fileName+":contextArray["+i+"]["+j+"]="+contextArray[i][j]);
					
				}
			}

			tc.setTagFreq(tagFreq);
			tc.setContextArray(contextArray);
		
			
			br.close();
			
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
			return false;
		}

		return true;
	}

	public int getFreq(int key, int symbol) {

		int index = Arrays.binarySearch(symbolTable,symbol);
		if (index <0)// error finding the symbol
			return 0;

		// Add the frequency
		int frequency = 0;
		if (tc.getTagFreq() != null)
			frequency = tc.getTagFreq()[index];
		return frequency;

	}

	public double getPossibility(int key, int prev, int cur) {
		double result = 0;

		int curIndex = Arrays.binarySearch(symbolTable,cur);
		int prevIndex = Arrays.binarySearch(symbolTable,prev);


		// return a lower value, not 0 to prevent data sparse
		if (tc == null || curIndex <0 || prevIndex <0
				|| tc.getContextArray()[prevIndex][curIndex] == 0
				|| tc.getTagFreq()[prevIndex] == 0)
			return 0.000001;
		
		int prevCurConFreq = tc.getContextArray()[prevIndex][curIndex];
		int prevFreq = tc.getTagFreq()[prevIndex];

		// 0.9 and 0.1 is a value based experience
		result = 0.9 * (double) prevCurConFreq;
		result /= (double) prevFreq;
		result += 0.1 * (double) prevFreq / (double) tc.getTotalFreq();

		return result;
	}



	

}
