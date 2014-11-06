package dk.sdu.kdtree;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

public class KDNode<T> {

	private final List<Number> keys;
	private final T value;
	private final int depth;
	private KDNode<T> leftChild, rightChild;
	
	
	KDNode(List<Number> keys, T value, int depth) {
		this.keys = keys;
		this.value = value;
		this.depth = depth;
	}
	
	public void put(List<Number> keys, T value){
		if(this.isHigher(keys)){
			if(leftChild == null) {
				leftChild = new KDNode<T>(keys, value, this.depth + 1);
			} else {
				leftChild.put(keys, value);
			}
		} else {
			if(rightChild == null) {
				rightChild = new KDNode<T>(keys, value, this.depth + 1);
			} else {
				rightChild.put(keys, value);
			}
		}
	}
	
	/**
	 * If current object is higher than other object which is the param returns true
	 * @param keys
	 * @return
	 */
	private boolean isHigher(List<Number> keys) {
		int i = depth % keys.size();
		return this.keys.get(i).doubleValue() > keys.get(i).doubleValue();
	}
	
	public int subSize(){
		int result = 1;
		if(this.leftChild != null) {
			result += this.leftChild.subSize();
		}
		if(this.rightChild != null) {
			result += this.rightChild.subSize();
		}
		return result;
	}

	public T getValue(){
		return this.value;
	}
	
	public List<Number> getKeys(){
		return this.keys;
	}

	public KDNode findClosest(List<Number> position,
			Number radius) {
		// TODO Auto-generated method stub
		return null;
	}
}
