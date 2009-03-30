package org.loonglab.segment.dictionary.impl;

import java.util.Comparator;

import org.ictclas4j.bean.WordItem;

public class UnicodeComparator implements Comparator<WordItem> {

	public int compare(WordItem s1, WordItem s2) {
		return compareTo(s1.getWord(),s2.getWord());
	}
	
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

}
