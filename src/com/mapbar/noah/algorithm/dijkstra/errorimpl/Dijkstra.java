package com.mapbar.noah.algorithm.dijkstra.errorimpl;

import java.util.ArrayList;
import java.util.List;
/**
 * It's an error implement!!!!!
 * @author lizn
 */
public class Dijkstra {

	public void proc() {
		Node node = all[1];
		addMinW(node);
	}

	public void addMinW(Node node) {
		Node minNode = node.getMinWNext();
		if (null == minNode)
			return;
		all[minNode.number] = null;
		path.add(minNode);
		addMinW(minNode);
	}

	public void add(Node node) {
		if (null == node)
			return;
		if (all.length < (node.number + 1)) {
			Node[] newAll = new Node[node.number + 1];
			for (int i = 0; i < all.length; i++) {
				newAll[i] = all[i];
			}
			all = newAll;
		}
		all[node.number] = node;
	}

	public void addNext(int num, Node next, int weight) {
		Node node = all[num];
		if (null == node)
			return;
		node.addNext(next, weight);
	}

	public Node getNode(int number) {
		return all[number];
	}

	Node[] all = new Node[0];
	List<Node> path = new ArrayList<Node>();
}