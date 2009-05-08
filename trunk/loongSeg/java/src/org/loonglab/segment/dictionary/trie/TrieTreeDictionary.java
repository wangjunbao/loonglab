package org.loonglab.segment.dictionary.trie;

import java.util.List;

import org.loonglab.segment.dictionary.Hit;
import org.loonglab.segment.dictionary.WordItem;
import org.loonglab.segment.util.ChineseEncoder;

public class TrieTreeDictionary {
	
	TrieNode[] words=new TrieNode[7000];
	
	public TrieTreeDictionary(WordItem[] wordItems) {
		buildTrieTree(wordItems);
	}
	
	
	
	private void buildTrieTree(WordItem[] wordItems) {
		for (WordItem wordItem : wordItems) {
			addWordItem(wordItem);
			
		}
		
	}

	private void addWordItem(WordItem wordItem) {
		String wordStr=wordItem.getWord();
		
		//TrieNode
		
	}



	/**
	 * 查找某词语在trie树中的位置，返回已存在的公共前缀的最后一个节点。返回null表示首字在词典中也不存在
	 * @param word
	 * @return
	 */
	private TrieNode search(String word){
		char firstChar=word.charAt(0);
		int firstKey=ChineseEncoder.hashCode(firstChar);
		
		TrieNode tn=words[firstKey];
		
		if(tn!=null){
			if(word.length()==1)
				return tn;
			
			for (int i = 1; i < word.length(); i++) {
				char c=word.charAt(i);
				TrieNode node=binarySearch(tn.subNodes, c);
				if(node==null){
					return tn;
				}
				else if(i==word.length()-1){
					return node;
				}
				else
					tn=node;
			}
		}
		
		return null;
	}
	
	/**
	 * 用二分法在子节点列表中查询对应某个字符的节点,返回null表示没有找到
	 * @param subNodes
	 * @param c
	 * @return
	 */
	private TrieNode binarySearch(List<TrieNode> subNodes,char c){
		int start=0;
		int end = subNodes.size() - 1;
		int mid = 0;
		
		while (start <= end) {
			mid = (start + end) / 2;
			TrieNode tn = subNodes.get(mid);
			int cmpValue = tn.getCh()-c;
			if (cmpValue == 0 ) {
				return tn;

			} else if (cmpValue < 0 ){						
				start = mid + 1;						
			}						
			else if (cmpValue > 0 ){						
				end = mid - 1;						
			}

		}
		
		return null;
				

	}

	public List<WordItem> allSplit(String sentence){
		return null;
	}
}
