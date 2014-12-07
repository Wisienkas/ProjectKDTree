package dk.sdu.kdtree;

public interface Dimensionable<T extends Distanceable>{
	public int getDimensions();
	public T getDimensionKey(int depth);
	public T[] getDimensionKeys();
	public T getDistance(Dimensionable<T> other);
	public T getAxisDistance(T other);
}
