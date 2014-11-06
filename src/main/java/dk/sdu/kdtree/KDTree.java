package dk.sdu.kdtree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * 
 * @author Wisienkas
 *
 * @param <T> the type of value
 */
public class KDTree<T> {
	
	private KDNode<T> root;
	private int size;
	private final int dimensions;
	
	/**
	 * initializes an empty KDTree
	 * @param dimensions
	 */
	public KDTree(int dimensions) {
		this(dimensions, new HashMap<>());
	}
	
	/**
	 * Initiates KDTree with a collection of entries.
	 * @param dimensions
	 * @param collection
	 */
	public KDTree(int dimensions, Map<List<Number>, T> collection) {
		this.size = 0;
		this.dimensions = dimensions;
		smartInit(collection);
	}

	/**
	 * Will find the median value, to make the tree more balanced from the start
	 * @param collection 
	 */
	private void smartInit(Map<List<Number>, T> collection) {
		
	}
	
	/**
	 * Adds the element to the 
	 * @param key
	 * @param value
	 */
	public void put(List<Number> keys, T value) throws IllegalArgumentException {
		if(keys.size() != this.dimensions){
			throw new IllegalArgumentException("Keys does not math dimensions");
		}
		if(root == null) {
			root = new KDNode<T>(keys, value, 0);
		} else {
			this.root.put(keys, value);
		}
		this.size++;
	}
	
	/**
	 * Find the elements closest to the position by pythagoras
	 * @param position
	 * @param radius
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public Optional<Map.Entry<List<Number>, T>> findClosest(List<Number> position, Number radius) throws IllegalArgumentException {
		if(position.size() != this.dimensions){
			throw new IllegalArgumentException("Keys does not math dimensions");
		}
		if(root == null){
			return Optional.empty();
		}
		return toOptinalEntry(root.findClosest(position, radius));
		
	}
	
	private Optional<Entry<List<Number>, T>> toOptinalEntry(KDNode<T> closest) {
		return Optional.of(new KDEntry<List<Number>, T>(closest.getKeys(), closest.getValue()));
	}

	/**
	 * Get the amount of dimensions
	 * @return
	 */
	public int getDimensions(){
		return this.dimensions;
	}
	
	/**
	 * fetch the size of the tree
	 * @return size of tree
	 */
	public int getSize(){
		return this.size;
	}

	/**
	 * This traversals through all the nodes to be sure it is that size
	 * @return actual size
	 */
	public int getSecureSize() {
		if(root == null){
			return 0;
		}
		return root.subSize();
	}
	
}
