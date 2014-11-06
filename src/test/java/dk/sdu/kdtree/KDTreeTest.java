package dk.sdu.kdtree;

import java.util.ArrayList;
import java.util.List;



import org.junit.Assert;
import org.junit.Test;

public class KDTreeTest {

	@Test
	public void testLegalPut() {
		KDTree<String> tree = new KDTree<>(2);
		
		add2dElement(2,3,"Nikolaj", tree);
		add2dElement(1,3,"Josefine", tree);
		add2dElement(6,3,"Thomas", tree);
		add2dElement(2,33,"Emil", tree);
		add2dElement(2,8,"Almir", tree);
		add2dElement(2,8,"Henrik", tree);
		
		Assert.assertEquals(tree.getSecureSize(), tree.getSize());
		Assert.assertEquals(6, tree.getSize());
	}

	private void add2dElement(int x, int y, String value, KDTree<String> tree) {
		List<Number> list = new ArrayList<>();
		list.add(x);
		list.add(y);
		
		tree.put(list, value);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIlligalPut(){
		KDTree<String> tree = new KDTree<>(3);
		List<Number> list = new ArrayList<>();
		list.add(2);
		
		tree.put(list, "Should Fail");
	}

}
