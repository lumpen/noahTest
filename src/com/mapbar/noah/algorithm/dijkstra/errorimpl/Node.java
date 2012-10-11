package com.mapbar.noah.algorithm.dijkstra.errorimpl;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public Node(){}
	public Node(int number){
		this.number = number;
	}
	int number;
	int weight = 0;
	List<Node> nexts = new ArrayList<Node>();
	List<Integer> nextweight = new ArrayList<Integer>();
	
	public Node getMinWNext(){
		Node node = null;
		int min = Integer.MAX_VALUE;
		Node[] nextNodes = nexts.toArray(new Node[0]);
		Integer[] nextNodeWeights = nextweight.toArray(new Integer[0]);
		for(int i = 0; i < nextNodes.length; i++){
			if(min >= nextNodeWeights[i]){
				node = nextNodes[i];
				min = nextNodeWeights[i];
			}
		}
		
		return node;
	}
	
	public void addNext(Node next, int weight){
		nexts.add(next);
		nextweight.add(weight);
	}
}
