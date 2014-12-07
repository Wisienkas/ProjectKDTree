package dk.sdu.kdtree.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import dk.sdu.kdtree.Dimensionable;
import dk.sdu.kdtree.Distanceable;
import dk.sdu.kdtree.KDNode;

public class Node<K extends Dimensionable<D>, D extends Distanceable, V>
		implements KDNode<K, D, V> {

	private K dimensionable;
	private Optional<KDNode<K, D, V>> leftChild, rightChild, parent;
	private int depth;
	private V value;
	private int underlyingNodes;

	@Override
	public int getUnderlyingNodes() {
		return this.underlyingNodes;
	}

	public Node(K dimensionable, V value) throws IllegalArgumentException {
		this.value = value;
		this.dimensionable = dimensionable;
		this.depth = 0;
		this.leftChild = Optional.empty();
		this.rightChild = Optional.empty();
		this.parent = Optional.empty();
		this.underlyingNodes = 0;
	}

	@Override
	public void setParent(KDNode<K, D, V> node) {
		this.parent = Optional.ofNullable(node);
	}

	@Override
	public void setLeftChild(KDNode<K, D, V> node) {
		node.setParent(this);
		this.leftChild = Optional.of(node);
	}

	@Override
	public void setRightChild(KDNode<K, D, V> node) {
		node.setParent(this);
		this.rightChild = Optional.of(node);
	}

	@Override
	public Optional<KDNode<K, D, V>> getOptLeftChild() {
		return this.leftChild;
	}

	@Override
	public Optional<KDNode<K, D, V>> getOptRightChild() {
		return this.rightChild;
	}

	@Override
	public Optional<KDNode<K, D, V>> getOptParent() {
		return this.parent;
	}

	@Override
	public int getDepth() {
		return this.depth;
	}

	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public boolean insert(KDNode<K, D, V> node) throws IllegalArgumentException {
		boolean result = false;
		if (node.getDimensionable()
				.getDimensionKey(this.getDepth())
				.isHigher(
						this.getDimensionable()
								.getDimensionKey(this.getDepth()))) {
			if (this.getOptRightChild().isPresent()) {
				result = this.getOptRightChild().get().insert(node);
			} else {
				this.setRightChild(node);
				result = true;
			}
		} else {
			if (this.getOptLeftChild().isPresent()) {
				result = this.getOptLeftChild().get().insert(node);
			} else {
				this.setLeftChild(node);
				result = true;
			}
		}
		if (result)
			this.underlyingNodes++;
		return result;
	}

	@Override
	public boolean remove(KDNode<K, D, V> node) throws IllegalArgumentException {
		boolean result = false;
		if (node.equals(this)) {
			if (getOptLeftChild().isPresent() || getOptRightChild().isPresent()) {
				upgradeChilds();
			}
		} else if (node.getDimensionable().getDimensionKey(depth)
				.isHigher(this.getDimensionable().getDimensionKey(depth))) {
			// Should go right
			if (this.getOptRightChild().isPresent()) {
				result = getOptRightChild().get().remove(node);
			} else {
				result = false;
			}
		} else {
			// Should go left
			if (this.getOptLeftChild().isPresent()) {
				result = getOptLeftChild().get().remove(node);
			} else {
				result = false;
			}
		}
		return result;
	}

	private void upgradeChilds() {
		UnsetOnParent();
		getOptLeftChild().ifPresent(node -> parent.get().insert(node));
		getOptRightChild().ifPresent(node -> parent.get().insert(node));
	}

	private void UnsetOnParent() {
		getOptParent().ifPresent(parent -> {
			parent.getOptLeftChild().ifPresent(leftChild -> {
				if (leftChild.equals(this)) {
					parent.setLeftChild(null);
				}
			});
			parent.getOptRightChild().ifPresent(rightChild -> {
				if (rightChild.equals(this)) {
					parent.setRightChild(null);
				}
			});
		});
	}

	@Override
	public boolean[] remove(KDNode<K, D, V>[] nodes)
			throws IllegalArgumentException {
		return null;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public K getDimensionable() {
		return this.dimensionable;
	}

	@Override
	public KDNode<K, D, V> findNearby(K point) throws IllegalArgumentException {
		KDNode<K, D, V> result = this;
		switch (getExpansionPath(point)) {
		case Right:
			if (rightChild.isPresent()) {
				if (!isCloserToPoint(point, result)) {
					result = getOptRightChild().get();
				}
			}
			break;
		case Both:
			if (rightChild.isPresent()) {
				if (!isCloserToPoint(point, result)) {
					result = getOptRightChild().get();
				}
			}
			if (leftChild.isPresent()) {
				if (!isCloserToPoint(point, result)) {
					result = getOptLeftChild().get();
				}
			}
			break;
		case Left:
			if (leftChild.isPresent()) {
				if (!isCloserToPoint(point, result)) {
					result = getOptLeftChild().get();
				}
			}
			break;
		}
		return result;
	}

	private boolean isCloserToPoint(K point, KDNode<K, D, V> current) {
		return point.getDistance(getOptRightChild().get().getDimensionable())
				.isHigher(point.getDistance(current.getDimensionable()));
	}

	private Expansion getExpansionPath(K point) {
		if(point.getDimensionKey(depth).isHigher(getDimensionable().getDimensionKey(depth))) {
			if(point.get)
		}
		Distanceable thisToPoint = point.getDistance(getDimensionable().getDimensionKey(depth));
		point.getDimensionKey(depth).isHigher(getDimensionable().getDimensionKey(depth))
		return 0;
	}

	@Override
	public List<KDNode<K, D, V>> findCircle(D radius, K points)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KDNode<K, D, V>> findSquare(K from, K to)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KDNode<K, D, V>> traversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KDNode<K, D, V>> traversal(K from, K to)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KDNode<K, D, V>> findAll(
			Function<K, List<KDNode<K, D, V>>> findAllFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KDNode<K, D, V>> find(Function<K, KDNode<K, D, V>> findFunction) {
		// TODO Auto-generated method stub
		return null;
	}

}
