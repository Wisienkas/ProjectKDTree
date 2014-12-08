package dk.sdu.kdtree;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface KDNode<K extends Dimensionable, V> {

	public boolean insert(KDNode<K, V> node) throws IllegalArgumentException;
	
	public boolean remove(KDNode<K, V> node) throws IllegalArgumentException;
	public boolean[] remove(KDNode<K, V>[] nodes) throws IllegalArgumentException;
	
	public void setParent(KDNode<K, V> node);
	public void setLeftChild(KDNode<K, V> node);
	public void setRightChild(KDNode<K, V> node);
	
	public Optional<KDNode<K, V>> getOptLeftChild();
	public Optional<KDNode<K, V>> getOptRightChild();
	public Optional<KDNode<K, V>> getOptParent();
	
	public int getDepth();
	public void setDepth(int depth);
	
	public int getUnderlyingNodes();
	
	public V getValue();
	
	public K getDimensionable();
	
	public KDNode<K, V> findNearby(K point) throws IllegalArgumentException;
	public KDNode<K, V> findNearby(K point, Distanceable radius) throws IllegalArgumentException;
	
	public List<KDNode<K, V>> findCircle(Distanceable radius, K points) throws IllegalArgumentException;
	public List<KDNode<K, V>> findSquare(K from, K to) throws IllegalArgumentException;
	
	public List<KDNode<K, V>> traversal();
	public List<KDNode<K, V>> traversal(K from, K to) throws IllegalArgumentException;

	public List<KDNode<K, V>> findAll(Function<K, List<KDNode<K, V>>> findAllFunction);
	public List<KDNode<K, V>> find(Function<K, KDNode<K, V>> findFunction);
	
}
