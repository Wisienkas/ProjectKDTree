package dk.sdu.kdtree;

public interface Distanceable<T> extends Comparable<T>{
	
	public T getDistance(T other);
	public T get();
	
}
