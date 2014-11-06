package dk.sdu.kdtree;



public class KDNode<T> {

	private final Double[] keys;
	private final T value;
	private final int depth;
	KDNode<T> leftChild, rightChild;
	
	
	KDNode(T value, int depth, Double... keys) {
		this.keys = keys;
		this.value = value;
		this.depth = depth;
	}
	
	public void put(T value, Double... keys){
		if(this.isHigher(keys)){
			if(leftChild == null) {
				leftChild = new KDNode<T>(value, this.depth + 1, keys);
			} else {
				leftChild.put(value, keys);
			}
		} else {
			if(rightChild == null) {
				rightChild = new KDNode<T>(value, this.depth + 1, keys);
			} else {
				rightChild.put(value, keys);
			}
		}
	}
	
	/**
	 * If current object is higher than other object which is the param returns true
	 * @param keys
	 * @return
	 */
	private boolean isHigher(Double...  keys) {
		int i = depth % keys.length;
		return this.keys[i] > keys[i];
	}
	
	public int subSize(){
		int result = 1;
		if(this.leftChild != null){
			result += this.leftChild.subSize();
		}
		if(this.rightChild != null){
			result += this.rightChild.subSize();
		}
		return result;
	}

	public T getValue(){
		return this.value;
	}
	
	public Double[] getKeys(){
		return this.keys;
	}

	public KDNode<T> findClosest(Double radius, KDNode<T> bestSoFar, Double...  position) {
		switch (getAxisCase(position, radius)) {
		case OVERLAP:
			bestSoFar = getBest(radius, bestSoFar, position);
			if(this.leftChild != null){
				bestSoFar = this.leftChild.findClosest(radius, bestSoFar, position);
			}
			if(this.rightChild != null){
				bestSoFar = this.rightChild.findClosest(radius, bestSoFar, position);
			}
			break;
		case RIGHT:
			if(this.rightChild != null){
				bestSoFar = this.rightChild.findClosest(radius, bestSoFar, position);
			}
			break;
		case LEFT:
			if(this.leftChild != null){
				bestSoFar = this.leftChild.findClosest(radius, bestSoFar, position);
			}
			break;
		}
		return bestSoFar;
	}

	private AxisCase getAxisCase(Double[] position, Double radius) {
		int index = depth % position.length;
		if(Math.abs(this.keys[index] - position[index]) < radius){
			return AxisCase.OVERLAP;
		} else if(this.keys[index] - position[index] < 0){
			return AxisCase.RIGHT;
		} else {
			return AxisCase.LEFT;
		}
	}

	private KDNode<T> getBest(Double radius, KDNode<T> bestSoFar, Double... position) {
		if(bestSoFar == null){
			return this;
		} else {
			return range(position) < bestSoFar.range(position) ? this : bestSoFar;
		}
	}

	private double range(Double[] position) {
		double c = 0;
		for(int i = 0; i < this.keys.length; i++) {
			c += Math.abs(this.keys[i] - position[i]);
		}
		return Math.sqrt(c);
	}
	
	public enum AxisCase{
		LEFT, RIGHT, OVERLAP;
	}
}
