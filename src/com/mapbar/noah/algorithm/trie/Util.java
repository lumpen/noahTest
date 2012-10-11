package com.mapbar.noah.algorithm.trie;

public class Util {
	public static final String allWord = "abcdefghigklmnopqrstuvwxyz";
	public static final char[] wordArr = allWord.toCharArray();
	
	public static Node[] arrAdd(Node[] origin, Node next){
		Node[] output = new Node[origin.length+1];
		for(int i = 0; i < origin.length; i++)
			output[i] = origin[i];
		output[origin.length] = next;
		return output;
	}
	
	/**
	 * 初始化一个root节点，二十六个字母为其子节点
	 * @return
	 */
	public static Node getRoot(){
		Node root = new Node();
		Node[] sons = new Node[Util.wordArr.length];
		for(int i = 0; i < sons.length; i++){
			Node son = new Node();
			son.word = Util.wordArr[i];
			sons[i] = son;
		}
		root.sons = sons;
		return root;
	}
	
	public static void insert(Node node, String word){
		word = word.toLowerCase();
		if(null == node || null == word || word.length() == 0)
			return;
		Node next = null;
		for(Node tn : node.sons){
			if(tn.word == word.charAt(0)){
				next = tn;
				break;
			}
		}
		if(null == next){
			next = new Node();
			next.word = word.charAt(0);
			node.sons = Util.arrAdd(node.sons, next);
		}
		if(word.length() == 1)
			next.wordNum++;
		insert(next, word.substring(1, word.length()));
	}
	
	public static int find(Node node, String word){
		word = word.toLowerCase();
		char c = word.charAt(0);
		Node next = new Node();
		for(Node nt : node.sons){
			if(nt.word == c){
				next = nt;
				break;
			}
		}
		if(word.length() == 1)
			return next.wordNum;
		return find(next, word.substring(1, word.length()));
	}
}
