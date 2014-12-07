package dk.sdu.kdtree;

public interface Distanceable{
	
	public Distanceable getDistance(Distanceable other);
	public boolean isHigher(Distanceable other);
	public Distanceable get();
	
}
