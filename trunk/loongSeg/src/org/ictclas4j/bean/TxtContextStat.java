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
 *
 */
public class TxtContextStat extends ContextStat{

	@Override
	public boolean load(String fileName, boolean isReset) {
		
		try {
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK"));
			
			tableLen=new Integer(br.readLine());
			
			symbolTable = new int[tableLen];
			String symbolStr=br.readLine();
			String[] symbolArray=symbolStr.split(" ");
			for (int j = 0; j < symbolArray.length; j++) {
				String sym = symbolArray[j];
				symbolTable[j]=new Integer(sym);
			}
			
			TagContext tc = new TagContext();
			int key=new Integer(br.readLine());
			int totalFreq =new Integer(br.readLine());
			
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
