package com.mapbar.noah.algorithm.trie;

public class Main {
	public static void main(String[] args) throws Exception{
		String[] words = new String[]{"I", "am", "a", "pig",
				"I", "like", "to", "eat",
				"If", "you", "have", "a", "delicious",
				"Please", "send", "me",
				"If", "I", "had", "a", "delicious",
				"I", "will", "not", "give", "you"};
//		String[] words = new String[]{
//				 "will", "not", "give", "you"};
		Node root = Util.getRoot();
		for(String word : words){
			Util.insert(root, word);
		}
		
		int num = Util.find(root, "you");
		System.out.println(num);
	}
}
