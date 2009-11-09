package com.kejikeji.common.bean;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatePropertyEditor extends PropertyEditorSupport {

	private static SimpleDateFormat test = new SimpleDateFormat("HH:mm");
	private static List<DateFormat> formats=new ArrayList<DateFormat>();
    static{
        // ISO formats
        formats.add(0,new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        formats.add(1,new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
        formats.add(2,new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"));
        formats.add(3,DateFormat.getDateTimeInstance());

        // XPDL examples format
        formats.add(4,new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US));

        // alternative formats
        formats.add(5,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        formats.add(6,new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        formats.add(7,new SimpleDateFormat("yyyy-MM-dd"));

        //Only date, no time
        formats.add(8,DateFormat.getDateInstance());
    }


	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		if (null == text || "".equals(text)) {
			// Treat empty String as null value.
			setValue(null);
		}else {
			Date value = null;
			for (DateFormat format:formats) {
	            try {
	                value = format.parse(text);
	                break;
	            } catch (ParseException e) {
//	                e.printStackTrace(System.out);
	            }
	        }
			setValue(value);
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		Date value = (Date) getValue();
		String valueString = "";
		if(test.format(value).equals("00:00")){
			valueString = formats.get(6).format(value);
		}else{
			valueString = formats.get(7).format(value);
		}
		return valueString;
	}

}
