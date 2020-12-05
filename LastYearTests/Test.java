import java.util.*;
import java.util.stream.Collectors;
public class Test {
	public static void main(String[] args) {
		//medidot1();
	  	medidot2();
		Scanner input = new Scanner(System.in);
		AVLTree T1 = new AVLTree();
		int[] arr2 = {1, 34, 98, 5, 10, 57, 92, 27, 9, 40, 64, 86, 25, 14, 39, 82, 83, 31, 3, 43, 64, 76, 40, 72, 20, 86};
		int forSplit = 34;
		/*int[] arr2 = {300941, 797955, 464650, 482935, 528592, 8256, 927555, 411941, 398372, 14951, 342967, 791150, 607371, 665786, 897731, 215023, 566347, 874283, 462657, 30307};
		int[] arr2 = {907160, 940213, 788860, 169186, 646190, 198150, 580910, 55462, 80859, 982611, 247915, 840489, 358947, 228369, 902599, 584995, 496869, 926391, 804235, 892219};
		int[] arr2 = {1, 34, 98, 5, 10, 57, 92, 27, 9, 40, 64, 86, 25, 14, 39, 82, 83, 31, 3, 43, 64, 76, 40, 72, 20, 86, 59, 66, 36, 39, 68, 33, 20, 61, 96, 0, 44, 78, 18, 33, 72, 22, 54, 26, 31, 93, 52, 22, 55, 65, 90, 11, 95, 28, 66, 67, 2, 50, 5, 20, 62, 33, 8, 39, 86, 70, 91, 46, 78, 79, 91, 86, 47, 41, 43, 18, 35, 41, 54, 98, 65, 68, 28, 92, 80, 9, 76, 30, 26, 39, 93, 30, 41, 90, 54, 73, 71, 47, 69, 36};
		int[] arr2 = {6, 73, 3, 60, 9, 85, 45, 54, 52, 98, 73, 1, 38, 89, 6, 65, 93, 74, 87, 17, 41, 66, 83, 16, 46, 59, 37, 51, 28, 75, 77, 97, 98, 92, 47, 19, 34, 44, 48, 58, 18, 52, 72, 76, 78, 7, 78, 10, 27, 22, 56, 39, 91, 45, 98, 52, 15, 99, 77, 60, 79, 5, 55, 98, 24, 59, 76, 5, 80, 75, 92, 96, 27, 61, 41, 74, 41, 49, 54, 80, 88, 29, 95, 95, 21, 23, 57, 93, 74, 74, 9, 44, 3, 84, 73, 59, 47, 28, 35, 95};
		int[] arr2 = {66, 54, 93, 99, 64, 82, 65, 56, 51, 46, 36, 9, 45, 30, 75, 98, 45, 70, 56, 3, 56, 55, 12, 9, 46, 20, 96, 63, 92, 58, 12, 86, 18, 4, 36, 99, 94, 76, 15, 73, 65, 28, 87, 37, 82, 67, 97, 11, 93, 7, 55, 72, 1, 3, 24, 5, 15, 39, 1, 31, 66, 44, 85, 56, 7, 15, 59, 27, 16, 59, 66, 57, 50, 58, 98, 0, 26, 13, 76, 63, 8, 14, 37, 68, 48, 45, 36, 65, 17, 31, 51, 50, 51, 93, 83, 44, 95, 2, 12, 20};
		int[] arr2 = {1,-2,3,-4};*/
		for(int i : arr2 ) {
			T1.insert(i, "1");
		}
		AVLTree.print2DUtil(T1.getRoot(),1);
		
		AVLTree[] trees = T1.split(forSplit, 10);
		System.out.println("tree1: ");
		AVLTree.print2DUtil(trees[0].getRoot(),1);
		System.out.println("tree2: ");
		AVLTree.print2DUtil(trees[1].getRoot(),1);
		AVLTree T2 = new AVLTree();
		
		
		/*System.out.println("enter number of nodes:");
		int n = input.nextInt();
		for(int i=0; i < n ; i++) {
			T2.insert(i, "1");
		} */
		
		//test delete
		AVLTree.print2DUtil(T2.getRoot(),1);
		System.out.println("enter key to delet: ");
		int del2= input.nextInt();
		int costDel2 = T2.delete(del2);
		System.out.println("cost del: " + costDel2);
		AVLTree.print2DUtil(T2.getRoot(),1);
		
		String todo;
		System.out.println("Enter key");
		String key = input.next();
		
		do {
			System.out.println("Enter key");			
//			System.out.println("Enter Value");
			String val = "1";
			
			int ins1 = T1.insert(Integer.parseInt(key), val);
			AVLTree.print2DUtil(T1.getRoot(),1);
			System.out.println("first insert cost: " + ins1);
			
			System.out.println("Enter next key, for exit press n");
			 key = input.next();
			
		}
		while(!key.equals("n"));
		
		
		AVLTree.print2DUtil(T1.getRoot(),1);
		
		//test delete
		System.out.println("enter key to delet: ");
		int del1 = input.nextInt();
		int costDel1 = T1.delete(del1);
		System.out.println("cost del: " + costDel1);
		AVLTree.print2DUtil(T1.getRoot(),1);
				
				
		System.out.println("enter spliting key:");
		int x = input.nextInt();
		/*AVLTree[] trees = T1.split(x);
		System.out.println("Smaller tree \n");
		AVLTree.print2DUtil(trees[0].getRoot(),1);
		System.out.println(Arrays.toString(trees[0].keysToArray()));
		System.out.println("Larger tree \n");
		AVLTree.print2DUtil(trees[1].getRoot(),1);
		System.out.println(Arrays.toString(trees[1].keysToArray()));*/

		
		/*AVLTree T2 = new AVLTree();
		do {
			System.out.println("Enter key");
			int key = input.nextInt();
			
			System.out.println("Enter Value");
			String val = input.next();
			
			int ins1 = T2.insert(key, val);
			AVLTree.print2DUtil(T2.getRoot(),1);
			System.out.println("first insert cost: " + ins1);
			
			System.out.println("Would you like to enter anther valuse to the tree, press y if you do");
			 todo = input.next();
			
		}
		while(todo.equals("y"));
		
		AVLTree Tester = new AVLTree();
		Tester.insert(3, "3");

		System.out.println("it cost:" + T1.join(Tester.getRoot(), T2));
		System.out.println("new tree is: \n\n");
		AVLTree.print2DUtil(T1.getRoot(),1);

		
		
		do {
			System.out.println("Enter key for deletion");
			int key = input.nextInt();
			
			int ins1 = T1.delete(key);
			AVLTree.print2DUtil(T1.getRoot(),1);
			System.out.println("first deletion cost: " + ins1);
			
			System.out.println("Would you like to delete anther valuse to the tree, press y if you do");
			 todo = input.next();
			
		}
		while(todo.equals("y"));
		

		System.out.println(Arrays.toString(T1.keysToArray()));
		*/
	}
	
	private static int[] randomArray(int size, int min, int max)
	{
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++)
		{
			int check = (int)(Math.random() * (max - min) + min);
			while(Arrays.asList(arr).contains(check)) {
				check = (int)(Math.random() * (max - min) + min);
			}
			arr[i] = check;
		}
		
		return arr;
	}
	public static void medidot1() {
		AVLTree tree = new AVLTree();
		for (int i = 1; i < 11; i++)
		{
			int num = i*10000;
			int[] values = randomArray(num, 0, 1000000);
			double sum = 0;
			double max = 0;
			int cost;
			for (int x : values)
			{
				cost = tree.insert(x, Integer.toString(x));
				if(cost > max) {
					max = cost;
				}
				sum +=cost;
			}
			System.out.println("\n\nmax cost for "+ num + " insertions is: "+ max);
			System.out.println("average cost for "+ num + " insertions is: "+ sum/num);
			Arrays.sort(values);
			sum = 0;
			max = 0;
			for (int x : values)
			{
				cost = tree.delete(x);
				if(cost > max) {
					max = cost;
				}
				sum +=cost;
			}
			System.out.println("max cost for "+ num + " delete is: "+ max);
			System.out.println("average cost for "+ num + " delete is: "+ sum/num);
		}
		
	}
	public static void medidot2() {
		for (int i = 1; i < 11; i++)
		{
			AVLTree tree = new AVLTree(); 
			int num = i*10000;
			int[] values = randomArray(num, 0, 1000000);
			//System.out.println("values: " + Arrays.toString(values));
			for (int x : values)
			{
				tree.insert(x, Integer.toString(x));
			}
			int index = (int)(Math.random() * (num));
			//AVLTree.print2DUtil(tree.getRoot(),1);
			System.out.println("\n\nrandom Node : " + values[index]);
			tree.split(values[index], num);
			
			
			
			
			AVLTree tree2 = new AVLTree();
			for (int x : values)
			{
				tree2.insert(x, Integer.toString(x));
			}
			AVLTree temp = new AVLTree(tree2.getRoot().getLeft());
			int maxKey = (AVLTree.maxRec(temp.getRoot()).getKey());
			//System.out.println("max Node: " + maxKey);
			tree2.split(maxKey, num);
		}
		
	}
}
