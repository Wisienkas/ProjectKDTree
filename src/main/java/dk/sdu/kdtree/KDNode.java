package dk.sdu.kdtree;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface KDNode<K extends Dimensionable<D>, D extends Distanceable, V> {

	public boolean insert(KDNode<K, D, V> node) throws IllegalArgumentException;
	
	public boolean remove(KDNode<K, D, V> node) throws IllegalArgumentException;
	public boolean[] remove(KDNode<K, D, V>[] nodes) throws IllegalArgumentException;
	
	public void setParent(KDNode<K, D, V> node);
	public void setLeftChild(KDNode<K, D, V> node);
	public void setRightChild(KDNode<K, D, V> node);
	
	public Optional<KDNode<K, D, V>> getOptLeftChild();
	public Optional<KDNode<K, D, V>> getOptRightChild();
	public Optional<KDNode<K, D, V>> getOptParent();
	
	public int getDepth();
	public void setDepth(int depth);
	
	public int getUnderlyingNodes();
	
	public V getValue();
	
	public K getDimensionable();
	
	public KDNode<K, D, V> findNearby(K point) throws IllegalArgumentException;
	public KDNode<K, D, V> findNearby(K point, D radius) throws IllegalArgumentException;
	
	public List<KDNode<K, D, V>> findCircle(D radius, K points) throws IllegalArgumentException;
	public List<KDNode<K, D, V>> findSquare(K from, K to) throws IllegalArgumentException;
	
	public List<KDNode<K, D, V>> traversal();
	public List<KDNode<K, D, V>> traversal(K from, K to) throws IllegalArgumentException;

	public List<KDNode<K, D, V>> findAll(Function<K, List<KDNode<K, D, V>>> findAllFunction);
	public List<KDNode<K, D, V>> find(Function<K, KDNode<K, D, V>> findFunction);
	
}
