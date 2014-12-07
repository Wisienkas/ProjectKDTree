package dk.sdu.kdtree;

import java.util.List;
import java.util.function.Function;

public interface KDNode<K extends Distanceable<D>, D, V> {

	public boolean insert(KDNode<K, D, V> node) throws IllegalArgumentException;
	
	public boolean remove(KDNode<K, D, V> node) throws IllegalArgumentException;
	public boolean[] remove(KDNode<K, D, V>[] nodes) throws IllegalArgumentException;
	
	public int getDimensions();
	
	public V getValue();
	
	public K[] getKeyValues();
	public K getKeyValue(int dimension);
	
	public KDNode<K, D, V> findNearby(K[] points) throws IllegalArgumentException;
	
	public List<KDNode<K, D, V>> findCircle(K radius, K[] points) throws IllegalArgumentException;
	public List<KDNode<K, D, V>> findSquare(K[] from, K[] to) throws IllegalArgumentException;
	
	public List<KDNode<K, D, V>> traversal();
	public List<KDNode<K, D, V>> traversal(K[] from, K[] to) throws IllegalArgumentException;

	public List<KDNode<K, D, V>> findAll(Function<K[], List<KDNode<K, D, V>>> findAllFunction);
	public List<KDNode<K, D, V>> find(Function<K[], KDNode<K, D, V>> findFunction);
	
}
