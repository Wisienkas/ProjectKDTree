package dk.sdu.kdtree;

import java.util.Map.Entry;

public class KDEntry<K, V> implements Entry<K, V> {

	private K k;
	private V v;

	KDEntry(K k, V v){
		this.k = k;
		this.v = v;
	}
	
	@Override
	public K getKey() {
		return k;
	}

	@Override
	public V getValue() {
		return v;
	}

	@Override
	public V setValue(V value) {
		return v;
	}
	
	
}
