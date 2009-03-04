package org.ictclas4j.segment;

import java.io.File;

import org.ictclas4j.bean.Dictionary;
import org.ictclas4j.bean.TxtContextStat;
import org.ictclas4j.bean.TxtDictionary;
import org.ictclas4j.utility.POSTag;
import org.ictclas4j.utility.Utility;
import org.ictclas4j.utility.Utility.TAG_TYPE;

public class TxtPosTagger extends PosTagger {

	public TxtPosTagger(TAG_TYPE type, String fileName, Dictionary coreDict) {
		if (fileName != null) {
			this.coreDict = coreDict;
			if (type == Utility.TAG_TYPE.TT_NORMAL)
				this.unknownDict = coreDict;
			else {
				//unknownDict = new Dictionary();
				unknownDict = new TxtDictionary();
				unknownDict.load(fileName + ".dct");
				
				File userFile=new File(fileName+".udt");
				if(userFile.exists()){
					//log.debug("load "+fileName+".udt...");
					unknownDict.loadUserDict(fileName+".udt");
				}
					
			}
			//用文本形式的词典
			//context = new ContextStat();
			context = new TxtContextStat();
			context.load(fileName + ".ctx");
			this.tagType = type;

			switch (type) {
			case TT_PERSON:
				// Set the special flag for transliterations
			case TT_TRANS_PERSON:
				pos = -POSTag.NOUN_PERSON;
				unknownFlags = "未##人";
				break;
			case TT_PLACE:
				pos = -POSTag.NOUN_SPACE;
				unknownFlags = "未##地";
				break;
			default:
				pos = 0;
				break;
			}
		}
	}

}
