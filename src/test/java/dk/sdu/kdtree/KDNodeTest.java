package dk.sdu.kdtree;

import org.junit.Assert;
import org.junit.Test;

public class KDNodeTest {


	@Test
	public void nodePutTest() {
		KDNode<String> root = new KDNode<String>("root", 0, 2d, 2d);
		
		root.put("rightChild", 3d, 3d);
		Assert.assertNotNull(root.rightChild);
		Assert.assertNull(root.leftChild);
		
		root.put("leftChild", 1d, 3d);
		Assert.assertNotNull(root.leftChild);
		
		root.put("rightRight", 3d, 4d);
		Assert.assertNotNull(root.rightChild.rightChild);
		Assert.assertNull(root.rightChild.leftChild);
		
		root.put("rightLeft", 4d, 2d);
		Assert.assertNotNull(root.rightChild.leftChild);
	}
	
	@Test
	public void nodeTestClosest(){
		KDNode<String> root = new KDNode<String>("root", 0, 2d, 2d);
		
		root.put("rightChild", 3d, 3d);
		
		root.put("leftChild", 1d, 3d);
		
		root.put("rightRight", 3d, 4d);
		
		root.put("rightLeft", 4d, 2d);

		KDNode<String> res = root.findClosest(4d, null, 5.2d, 5.2d);
		Assert.assertSame(res, root.rightChild.rightChild);
		
		res  = root.findClosest(4d, null, 5.2d, 2d);
		Assert.assertSame(res, root.rightChild.leftChild);

		res  = root.findClosest(1d, null, 5.2d, 2d);
		Assert.assertNull(res);

	}

}
