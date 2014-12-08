package dk.sdu.kdtree;

public interface Dimensionable {
	public int getDimensions();
	public Distanceable getDimensionKey(int depth);
	public Distanceable[] getDimensionKeys();
	public Distanceable getDistance(Dimensionable other);
	public Distanceable getAxisDistance(Distanceable other);
}
