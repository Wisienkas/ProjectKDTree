package dk.sdu.kdtree.impl;

import java.util.List;
import java.util.function.Function;

import dk.reibke.kdtree.Distanceable;
import dk.reibke.kdtree.KDNode;

public class Node<K extends Distanceable<D>, D, V> implements KDNode<K, D, V> {
	
	private int dimensions;
	private K[] keys;

	public Node(int dimensions, K[] keys, V value) {
		this.dimensions = dimensions;
		this.keys = keys;
	}

}
