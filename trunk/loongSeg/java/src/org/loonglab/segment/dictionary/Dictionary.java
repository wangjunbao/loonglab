package org.loonglab.segment.dictionary;

/**
 * 字典接口
 * @author loonglab
 *
 */
public interface Dictionary {
	/**
	 * 在字典中查询某词条
	 * @param word
	 * @return
	 */
	public Hit search(String word);
	
}
