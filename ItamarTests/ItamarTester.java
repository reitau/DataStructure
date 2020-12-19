import Tree.AVLTree;
import Tree.AVLTree.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ItamarTester {
    public static int getNodeHeight(IAVLNode node) {
        if (!node.isRealNode())
            return -1;

        return 1 + Math.max(getNodeHeight(node.getRight()), getNodeHeight(node.getLeft()));
    }

    public static int getTreeSize(IAVLNode node) {
        if (node == null || !node.isRealNode())
            return 0;

        return 1 + getTreeSize(node.getRight()) + getTreeSize(node.getLeft());
    }

    public static int getTreeMin(IAVLNode node) {
        int min = node.getKey();
        if (node.getRight().isRealNode())
            min = Math.min(min, getTreeMin(node.getRight()));
        if (node.getLeft().isRealNode())
            min = Math.min(min, getTreeMin(node.getLeft()));
        return min;
    }

    public static int getTreeMax(IAVLNode node) {
        int max = node.getKey();
        if (node.getRight().isRealNode())
            max = Math.max(max, getTreeMax(node.getRight()));
        if (node.getLeft().isRealNode())
            max = Math.max(max, getTreeMax(node.getLeft()));
        return max;
    }

    public static boolean isAVLTree(IAVLNode node) {
        if (!node.isRealNode())
            return true;

        // validate internal state
        int nodeHeight = getNodeHeight(node);
        assert node.getHeight() == nodeHeight;
        assert node.getRight().getParent() == node;
        assert node.getLeft().getParent() == node;
        assert node.getSize() == getTreeSize(node);

        // validate bst
        assert !node.getRight().isRealNode() || node.getRight().getKey() > node.getKey();
        assert !node.getLeft().isRealNode() || node.getLeft().getKey() < node.getKey();

        // validate AVL condition
        if (Math.abs(getNodeHeight(node.getLeft()) - getNodeHeight(node.getRight())) > 1)
            return false;
        return isAVLTree(node.getRight()) && isAVLTree(node.getLeft());
    }

    public boolean isAVLTree(AVLTree tree) {
        assert tree.size() == getTreeSize(tree.getRoot());
        return tree.empty() || isAVLTree(tree.getRoot());
    }

    public AVLTree generateTree(int minVal, int maxVal, int n) throws Exception{
        AVLTree tree = new AVLTree();
        for (int i = 0; i < n; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(minVal, maxVal);
            tree.insert(randomNum, String.valueOf(randomNum));
        }
        return tree;
    }

    public Set<Integer> keySet(AVLTree tree) {
        Set<Integer> keys = new HashSet<>();
        for (int key : tree.keysToArray())
            keys.add(key);
        return keys;
    }

    @Test
    public void testBasic() throws Exception {
        AVLTree tree = generateTree(1, 1000, 100);
        int[] keys = tree.keysToArray();
        int min = Arrays.stream(keys).min().getAsInt();
        int max = Arrays.stream(keys).max().getAsInt();

        assert Integer.parseInt(tree.min()) == min;
        assert Integer.parseInt(tree.max()) == max;
        assert min == keys[0];
        assert max == keys[keys.length - 1];

        String[] values = tree.infoToArray();
        assert values.length == keys.length;

        for (int i = 0; i < keys.length; i++) {
            assert keys[i] == Integer.parseInt(values[i]);
            if (i != keys.length - 1)
                assert keys[i] < keys[i + 1];
        }
    }

    @Test
    public void testBasicEmpty() {
        AVLTree tree = new AVLTree();
        int[] keys = tree.keysToArray();
        String[] values = tree.infoToArray();

        assert values.length == 0;
        assert keys.length == 0;
        assert tree.min() == null;
        assert tree.max() == null;
    }

    @Test
    public void testInsert() throws Exception{
        AVLTree tree = new AVLTree();

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
            if (numbers.contains(randomNum)) {
                assert -1 == tree.insert(randomNum, String.valueOf(randomNum));
            } else {
                assert -1 != tree.insert(randomNum, String.valueOf(randomNum));
                assert isAVLTree(tree);
                numbers.add(randomNum);
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        final int MAX_SIZE = 1000;
        AVLTree tree = new AVLTree();

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < MAX_SIZE; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, MAX_SIZE);
            if (!numbers.contains(randomNum)) {
                tree.insert(randomNum, String.valueOf(randomNum));
                numbers.add(randomNum);
                assert isAVLTree(tree);
            }
        }

        for (int i = 0; i < numbers.size() - 1; i++) {
            int randomKeyIndex = ThreadLocalRandom.current().nextInt(0, numbers.size() - 1);
            int randomKey = numbers.get(randomKeyIndex);

            tree.delete(randomKey);
            assert isAVLTree(tree);
            numbers.remove(randomKeyIndex);
        }
    }

    @Test
    public void testJoin() throws Exception{
        AVLTree dummy = new AVLTree();

        for (int i = 0; i < 1000; i++) {
            int n1 = ThreadLocalRandom.current().nextInt(0, 1000);
            int range1_lower = ThreadLocalRandom.current().nextInt(1, 10000);
            int range1_upper = range1_lower + n1 * 100;

            int x_val = ThreadLocalRandom.current().nextInt(range1_upper + 1, range1_upper + 500);
            IAVLNode x = dummy.new AVLNode(x_val, String.valueOf(x_val));

            int n2 = ThreadLocalRandom.current().nextInt(0, 1000);
            int range2_lower = ThreadLocalRandom.current().nextInt(x_val + 1, x_val + 10000);
            int range2_upper = range2_lower + n2 * 100;

            AVLTree tree1 = generateTree(range1_lower, range1_upper, n1);
            AVLTree tree2 = generateTree(range2_lower, range2_upper, n2);

            assert tree1.size() == 0 || Integer.parseInt(tree1.max()) < x_val;
            assert tree2.size() == 0 || Integer.parseInt(tree2.min()) > x_val;
            assert isAVLTree(tree1);
            assert isAVLTree(tree2);

            int tree2Size = tree2.size();
            int tree1Size = tree1.size();

            int join1 = ThreadLocalRandom.current().nextInt(0, 2);
            if (join1 == 0) {
                Set<Integer> tree2KeySet = keySet(tree2);

                tree1.join(x, tree2);
                assert isAVLTree(tree1);
                assert keySet(tree1).containsAll(tree2KeySet);
                assert tree1.size() == tree1Size + tree2Size + 1;
            } else {
                Set<Integer> tree1KeySet = keySet(tree1);

                tree2.join(x, tree1);
                assert isAVLTree(tree2);
                assert keySet(tree2).containsAll(tree1KeySet);
                assert tree2.size() == tree1Size + tree2Size + 1;
            }
        }
    }

    @Test
    public void testJoinMultiple() throws Exception{
        AVLTree dummy = new AVLTree();

        AVLTree joinedTree = new AVLTree();

        for (int i = 0; i < 50; i++) {
            int range_lower;
            if (joinedTree.empty())
                range_lower = ThreadLocalRandom.current().nextInt(1, 50);
            else
                range_lower = getTreeMax(joinedTree.getRoot()) + 1;

            int x_val = ThreadLocalRandom.current().nextInt( range_lower, range_lower + 500);
            IAVLNode x = dummy.new AVLNode(x_val, String.valueOf(x_val));

            range_lower = ThreadLocalRandom.current().nextInt( x_val + 1, x_val + 500);
            int n = ThreadLocalRandom.current().nextInt(0, 1000);
            int range_upper = range_lower + n * 100;
            AVLTree newTree = generateTree(range_lower, range_upper, n);

            assert isAVLTree(joinedTree);
            assert isAVLTree(newTree);
            assert joinedTree.size() == 0 || Integer.parseInt(joinedTree.max()) < x_val;
            assert newTree.size() == 0 || Integer.parseInt(newTree.min()) > x_val;


            int joinedTreeSize = joinedTree.size();
            int newTreeSize = newTree.size();
            Set<Integer> newTreeSizeKeySet = keySet(newTree);

            joinedTree.join(x, newTree);
            assert isAVLTree(joinedTree);
            assert keySet(joinedTree).containsAll(newTreeSizeKeySet);
            assert joinedTree.size() == joinedTreeSize + newTreeSize + 1;
        }
    }

    @Test
    public void testSplitTree() throws Exception{
        for (int i = 0; i < 10000; i++) {
            int n = ThreadLocalRandom.current().nextInt(0, 1000);
            int range_lower = ThreadLocalRandom.current().nextInt(1, 10000);
            int range_upper = range_lower + n * 100;

            AVLTree tree = generateTree(range_lower, range_upper, n);
            if (tree.empty())
                continue;

            int[] keySet = tree.keysToArray();
            int randomKey = keySet[ThreadLocalRandom.current().nextInt(0, keySet.length)];

            AVLTree[] trees = tree.split(randomKey);
            AVLTree smaller = trees[0];
            AVLTree bigger = trees[1];

            assert isAVLTree(smaller);
            assert isAVLTree(bigger);
            assert smaller.size() + bigger.size() + 1 == keySet.length;

            int[] smallerKeys = smaller.keysToArray();
            int[] biggerKeys = bigger.keysToArray();

            if (smallerKeys.length > 0) {
                int smallerMin = Arrays.stream(smallerKeys).min().getAsInt();
                int smallerMax = Arrays.stream(smallerKeys).max().getAsInt();
                assert smallerMax < randomKey;
                assert Integer.parseInt(smaller.min()) == smallerMin;
                assert Integer.parseInt(smaller.max()) == smallerMax;
            } else {
                assert smaller.min() == null;
                assert smaller.max() == null;
            }

            if (biggerKeys.length > 0) {
                int biggerMin = Arrays.stream(biggerKeys).min().getAsInt();
                int biggerMax = Arrays.stream(biggerKeys).max().getAsInt();
                assert biggerMin > randomKey;
                assert Integer.parseInt(bigger.min()) == biggerMin;
                assert Integer.parseInt(bigger.max()) == biggerMax;
            } else {
                assert bigger.min() == null;
                assert bigger.max() == null;
            }
        }
    }
}
