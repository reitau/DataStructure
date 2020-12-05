import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class AVLTreeTest {

	private static int[] randomArray(int size, int min, int max)
	{
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (int)(Math.random() * (max - min) + min);
		}
		
		return arr;
	}
	
	private static AVLTree arrayToTree(int[] arr)
	{
		//System.out.println("tree from " + Arrays.toString(arr));
		AVLTree tree = new AVLTree();
		for (int x : arr)
		{
			//System.out.println("inserting " + x);
			tree.insert(x, Integer.toString(x));
		//	assertTrue(AVLSanitizer.sanitizeTree(tree));
		}
		
		return tree;
	}
	
	@Test
	void testEmpty() {
		AVLTree tree = new AVLTree();
		
		assertTrue(tree.empty());
		
		tree.insert(1, "1");
		assertFalse(tree.empty());
		
		tree.delete(1);
		assertTrue(tree.empty());
	}

	@Test
	void testSearch() {
		AVLTree tree = new AVLTree();
		
		assertEquals(tree.search(1), null);
		
		tree.insert(1, "1");
		assertEquals(tree.search(1), "1");
		
		tree.delete(1);
		assertEquals(tree.search(1), null);
	}

	@Test
	void testInsert() {
		AVLTree tree = new AVLTree();
		
		assertEquals(tree.insert(1, "1"), 0);
		assertEquals(tree.insert(1, "1"), -1);
		
		assertEquals(tree.insert(2, "2"), 0);
		// RR
		assertEquals(tree.insert(3, "3"), 1);
	}

	@Test
	void testDelete() {
		AVLTree tree = new AVLTree();
		
		assertEquals(tree.delete(1), -1);
		
		tree.insert(1, "1");
		assertEquals(tree.delete(1), 0);
		
		tree.insert(2, "2");
		tree.insert(1, "1");
		tree.insert(3, "3");
		tree.insert(4, "4");
		// should be RR rotation
		int c = tree.delete(1);
		assertEquals(c, 3);
		
		assertTrue(AVLSanitizer.sanitizeTree(tree));
		
		int[] values = randomArray(100, 0, 100);
		tree = arrayToTree(values);
		List<Integer> valuesShuffled = Arrays.stream(values).boxed().collect(Collectors.toList());
		Collections.shuffle(valuesShuffled);
		for (int x : valuesShuffled)
		{
			System.out.format("deleting %d%n", x);
			tree.delete(x);
			assertTrue(AVLSanitizer.sanitizeTree(tree));
		}
	}

	// Simulate random insert and delete operations
	@Test
	void testInsertAndDelete() {
		AVLTree tree = new AVLTree();
		
		for (int tries = 0; tries < 50; tries++)
		{
			int[] values = randomArray(100, -30, 30);
			System.out.println(Arrays.toString(values));
			List<Integer> valuesShuffled = Arrays.stream(values).boxed().collect(Collectors.toList());
			Collections.shuffle(valuesShuffled);
			
			List<Integer> valuesShuffled2 = new ArrayList<Integer>(valuesShuffled);
			Collections.shuffle(valuesShuffled2);
			/*for (int j = 0; j < 5; j++)
			{
				for (int i = 0; i < valuesShuffled.size(); i++)
				{
					tree.insert(valuesShuffled.get(i), Integer.toString(valuesShuffled.get(i)));
					tree.delete(valuesShuffled2.get(i));
					assertTrue(tree.sanitize());
				}
			}*/
			for (int x : valuesShuffled)
			{
				System.out.println("x is: " + x);
				if (x < 0) {
					//System.out.println("tree before delete: ");
					//AVLTree.print2DUtil(tree.getRoot(),1);
					tree.delete(-x);
				}
				else {
					//System.out.println("tree before insert: ");
					//AVLTree.print2DUtil(tree.getRoot(),1);
					tree.insert(x, Integer.toString(x));
				}
			}
		}
	}

	@Test
	void testMinMax() {
		for (int i = 0; i < 10; i++)
		{
			int[] values = randomArray(100, 0, 100);
		
			//System.out.println("values: " + Arrays.toString(values));
			AVLTree tree = arrayToTree(values);
			values = Arrays.stream(values).distinct().toArray();
			Arrays.sort(values);
			//System.out.println("sorted values:" + Arrays.toString(values));
			
			System.out.println("checking min max");
		    assertEquals(tree.max(), Integer.toString(values[values.length - 1]));
			assertEquals(tree.min(), Integer.toString(values[0]));
			
			System.out.println("deleting min max");
			int test = Integer.parseInt(tree.max());
			tree.delete(test);
			//System.out.println("\nnew max:" + tree.max() + "should be:" + Integer.toString(values[values.length - 2]));
			assertEquals(tree.max(), Integer.toString(values[values.length - 2]));
			
			tree.delete(Integer.parseInt(tree.min()));
			//System.out.println("\nnew min:" + tree.min() + "should be:" + Integer.toString(values[1]));
			assertEquals(tree.min(), Integer.toString(values[1]));
		}
		
		System.out.println("checking empty tree");
		//Empty tree
		AVLTree tree = new AVLTree();
		assertEquals(tree.max(), null);
		assertEquals(tree.min(), null);
	}

	@Test
	void testKeysToArray() {
		int[] values = randomArray(100, 0, 100);
		AVLTree tree = arrayToTree(values);
		Arrays.sort(values);
		
		assertArrayEquals(tree.keysToArray(), Arrays.stream(values).distinct().toArray());
	}

	@Test
	void testInfoToArray() {
		int[] values = randomArray(100, 0, 100);
		AVLTree tree = arrayToTree(values);
		Arrays.sort(values);
		
		assertArrayEquals(tree.infoToArray(), Arrays.stream(values).distinct().mapToObj(x -> Integer.toString(x)).toArray());
	}

	@Test
	void testSize() {
		int[] values = randomArray(100, 0, 100);
		AVLTree tree = arrayToTree(values);
		int realSize = (int)Arrays.stream(values).distinct().count();

		System.out.println(realSize + " vs " + tree.size());
		assertEquals(tree.size(), realSize);
	}

	@Test
	void testGetRoot() {
		AVLTree tree = new AVLTree();
		assertEquals(null, tree.getRoot());
		
		tree.insert(1, "1");
		assertEquals(1, tree.getRoot().getKey());
		
		tree.insert(2, "2");
		assertEquals(1, tree.getRoot().getKey());
		
		tree.delete(1);
		assertEquals(2, tree.getRoot().getKey());
		
		assertEquals(null, tree.getRoot().getParent());
	}

}
