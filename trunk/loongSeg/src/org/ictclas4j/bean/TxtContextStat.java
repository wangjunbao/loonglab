package org.ictclas4j.bean;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.ictclas4j.utility.GFCommon;
import org.ictclas4j.utility.Utility;

/**
 * 相邻角色之间相关度
 * @author loonglab
 * ==============================构成人名的角色=================================
Tag = B(  1), Count =   513,    姓氏
Tag = C(  2), Count =   955,    双名的首字
Tag = D(  3), Count = 1,043,    双名的末字
Tag = E(  4), Count =   574,    单名
Tag = F(  5), Count =     3,    前缀
Tag = G(  6), Count =     9,    后缀
*Tag = K( 10), Count =     0,   人名的上文
Tag = L( 11), Count = 1,198,    人名的下文
Tag = M( 12), Count = 1,684,    两个中国人名之间的成分
Tag = N( 13), Count =    67,    <无>
*Tag = U( 20), Count =     0,   人名的上文与姓氏成词
*Tag = V( 21), Count =     0,   人名的末字与下文成词
Tag = X( 23), Count =    84,    姓与双名首字成词
Tag = Y( 24), Count =    47,    姓与单名成词
Tag = Z( 25), Count =   388,    双名本身成词
Tag = m( 44), Count =    58,    <无>
Tag = *(100), Count =     1,    始##始
Tag = *(101), Count =     1,    末##末 
Tag = A(  0), Count = 1061971,  普通词
 */
public class TxtContextStat extends ContextStat{
	
	

	@Override
	public boolean load(String fileName, boolean isReset) {
		
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
			TagContext tc = new TagContext();
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
			tcList.add(tc);
			
			
			
			br.close();
			
		} catch (Exception e1) {
			log.error(e1.getMessage(),e1);
			return false;
		}

		return true;

	}

	public TxtContextStat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
