import java.util.Arrays;
import java.util.Random;


public class combinedTester {
	
	public static void main(String[] args) {		
		joiner();
		
		if (false) {
			int[] arr1 = {229, 228, 228, 219, 224, 220, 220, 220, 228, 212};
			int[] arr2 = {206, 183, 207, 188, 200, 181, 186, 199, 200, 186};
			AVLTree tree1 = arrayToTree(arr1);
			AVLTree tree2 = arrayToTree(arr2);
			AVLTree.IAVLNode virtualLeaf1 = tree1.new AVLNode();
			AVLTree.IAVLNode node1 = tree1.new AVLNode(null, 0, 209, "");
			tree1.join(node1, tree2);
			print(isLegal(tree1));
		}
		
		splitter();
		
		
		if (false) {
			int[] arr = {125, 131, 145, 136, 131, 127, 132, 141, 137, 139};
			AVLTree tree = arrayToTree(arr);
			int splitPoint = 131;
			System.out.println("splitPoint = " + splitPoint);
			AVLTree[] trees = tree.split(splitPoint);
			AVLTree tree1 = trees[0];
			AVLTree tree2 = trees[1];
			print(isLegal(tree1) && isLegal(tree2));
			
		}
		
		
		
		}
	
	public static void joiner() {
		AVLTree[] trees = new AVLTree[100];
		for (int i = 0; i < 100; i++) {
			int[] arr = randomArray(5, 300*i, 300*i + 280);
			System.out.println("i = " + i);
			trees[i] = arrayToTree(arr);
			AVLTree.print2DUtil(trees[i].getRoot(),1);
			System.out.println(isLegal(trees[i]));
			if (i%2 == 1) {
				AVLTree tree = trees[i];
				AVLTree.IAVLNode virtualLeaf = tree.new AVLNode();
				AVLTree.IAVLNode node = tree.new AVLNode(null, 0, 300*i - 1, Integer.toString(i));
				tree.join(node, trees[i-1]);
				if (!(isLegal(tree))) {
					check(tree, trees[i-1], node);
					return;
				}
			}
		}
	
	}
	public static void check(AVLTree t1, AVLTree t2, AVLTree.IAVLNode node) {
		System.out.println(node.getKey());
		t1.join(node, t2);
		
	}
	
	public static void splitter() {
		for (int i = 0; i < 100; i++) {
			int[] arr = randomArray(100, 300*i, 300*i + 280);
			System.out.println("i = " + i);
			AVLTree tree = arrayToTree(arr);
			Random generator = new Random();
			int randomIndex = generator.nextInt(arr.length);
			int splitPoint = arr[randomIndex]; 
			System.out.println("splitPoint = " + splitPoint);
			AVLTree[] trees = tree.split(splitPoint,0);
			AVLTree tree1 = trees[0];
			AVLTree tree2 = trees[1];
			boolean leg =isLegal(tree1) && isLegal(tree2);
			print(leg);
			if (!(leg)) {
				return;
			}
			
		}
		
		
		
	}
	
	public static void print(boolean b) {
		System.out.println(b);
	}
	
	
	public static boolean isBST(AVLTree T) {
		if (!(T.getRoot().isRealNode())) {
			return true;
		}
		AVLTree rightT = new AVLTree(T.getRoot().getRight());
		AVLTree leftT = new AVLTree(T.getRoot().getLeft());
		boolean order = true;
		if (rightT.getRoot().isRealNode()) {
			order = (T.getRoot().getKey() > leftT.getRoot().getKey() &&
					T.getRoot().getKey() < rightT.getRoot().getKey());
		} else {
			order = (T.getRoot().getKey() > leftT.getRoot().getKey());
		}
		boolean pointers = ((!(T.getRoot().getRight().isRealNode()) ||
				T.getRoot().getRight().getParent() == T.getRoot()) &&
				(!(T.getRoot().getLeft().isRealNode()) ||
				T.getRoot().getLeft().getParent() == T.getRoot()));
		return (order && pointers &&
				isBST(rightT) && isBST(leftT));
		
	}
	public static boolean isLegal(AVLTree T) {
		if (!(T.getRoot().isRealNode())) {
			return true;
		}
		System.out.println("\nroot is: "+ T.getRoot().getKey());
		System.out.println("right is: "+ T.getRoot().getRight().getKey());
		System.out.println("left is: "+ T.getRoot().getLeft().getKey());
		AVLTree rightT = new AVLTree(T.getRoot().getRight());
		AVLTree leftT = new AVLTree(T.getRoot().getLeft());
		int hr = rightT.getRoot().getHeight();
 		int hl = leftT.getRoot().getHeight();
		System.out.println("HR " + rightT.getRoot().getHeight());
		System.out.println("HL " + leftT.getRoot().getHeight());

		boolean rootHeight = (T.getRoot().getHeight() ==
				max(rightT.getRoot().getHeight(), leftT.getRoot().getHeight()) + 1);
		boolean avl = Math.abs(rightT.getRoot().getHeight() - leftT.getRoot().getHeight()) <= 1;
		boolean size = (((AVLTree.AVLNode)T.getRoot()).getSize() ==
				((AVLTree.AVLNode)rightT.getRoot()).getSize() + ((AVLTree.AVLNode)leftT.getRoot()).getSize() + 1);
		boolean order = true;
		if (rightT.getRoot().isRealNode()) {
			order = (T.getRoot().getKey() > leftT.getRoot().getKey() &&
					T.getRoot().getKey() < rightT.getRoot().getKey());
		} else {
			order = (T.getRoot().getKey() > leftT.getRoot().getKey());
		}
		System.out.println("\n right son is ok: "+ (!(T.getRoot().getRight().isRealNode()) ||
				T.getRoot().getRight().getParent() == T.getRoot()));
		System.out.println("\n left son is ok: "+ (!(T.getRoot().getLeft().isRealNode()) ||
				T.getRoot().getLeft().getParent() == T.getRoot()));
		boolean pointers = ((!(T.getRoot().getRight().isRealNode()) ||
				T.getRoot().getRight().getParent() == T.getRoot()) &&
				(!(T.getRoot().getLeft().isRealNode()) ||
				T.getRoot().getLeft().getParent() == T.getRoot()));
		//System.out.println("\nroot is: "+ T.getRoot().getKey());
		System.out.println("rootHeight: "+ rootHeight);
		System.out.println("avl: "+ avl);
		System.out.println("size: "+ size);
		System.out.println("order: "+ order);
		System.out.println("pointers: "+ pointers);
		return (rootHeight && avl && size && order && pointers &&
				isLegal(leftT) && isLegal(rightT));
	}
	
	private static int max(int height, int height2) {
		if (height > height2) {
			return height;
		}
		return height2;
	}

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
		System.out.println("tree from " + Arrays.toString(arr));
		AVLTree tree = new AVLTree();
		for (int x : arr)
		{
			//System.out.println("inserting " + x);
			tree.insert(x, Integer.toString(x));
			//assertTrue(AVLSanitizer.sanitizeTree(tree));
		}
		
		return tree;
	}

}
