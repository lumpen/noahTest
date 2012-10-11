package com.mapbar.noah.algorithm.dijkstra.errorimpl;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Dijkstra d = new Dijkstra();
		init(d);
		d.proc();
		List<Node> p = d.path;
		for(Node n : p){
			System.out.println(n.number);
		}
	}
	
//	public static void init(Dijkstra d){
//		for(int i = 1; i <= 9; i++){
//			Node node = new Node(i);
//			d.add(node);
//		}
//		d.addNext(1, d.getNode(2), 2);
//		d.addNext(1, d.getNode(4), 9);
//		d.addNext(1, d.getNode(5), 6);
//		
//		d.addNext(2, d.getNode(3), 1);
//		d.addNext(2, d.getNode(5), 3);
//		
//		d.addNext(3, d.getNode(5), 1);
//		d.addNext(3, d.getNode(7), 6);
//		
//		d.addNext(4, d.getNode(8), 4);
//		
//		d.addNext(5, d.getNode(4), 2);
//		d.addNext(5, d.getNode(8), 7);
//		d.addNext(5, d.getNode(6), 9);
//		
//		d.addNext(6, d.getNode(7), 5);
//		d.addNext(6, d.getNode(9), 1);
//		
//		d.addNext(7, d.getNode(9), 5);
//		
//		d.addNext(8, d.getNode(6), 1);
//		d.addNext(8, d.getNode(9), 5);
//	}
	
	public static void init(Dijkstra d){
		for(int i = 1; i <= 6; i++){
			Node node = new Node(i);
			d.add(node);
		}
		
		d.addNext(1, d.getNode(2), 2);
		d.addNext(1, d.getNode(4), 4);
		
		d.addNext(2, d.getNode(3), 3);
		d.addNext(2, d.getNode(6), 6);
		
		d.addNext(3, d.getNode(6), 5);
		
		d.addNext(4, d.getNode(5), 6);
		
		d.addNext(5, d.getNode(6), 2);
	}
}
