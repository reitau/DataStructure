import AVLTree.AVLTree;
import AVLTree.AVLTree.*;
import AVLTree.TreePrinter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MyTester {
    public void printTree(AVLTree tree) {
        TreePrinter<IAVLNode> printer =
                new TreePrinter<IAVLNode>(n -> String.valueOf(n.getKey()),
                        (n -> n.getLeft() != null && n.getLeft().isRealNode() ? n.getLeft() : null),
                        (n -> n.getRight() != null && n.getRight().isRealNode() ? n.getRight() : null));

        printer.setSquareBranches(true);

        printer.printTree(tree.getRoot());
    }

    public int getNodeHeight(IAVLNode node) {
        if (!node.isRealNode())
            return -1;

        return 1 + Math.max(getNodeHeight(node.getRight()), getNodeHeight(node.getLeft()));
    }

    public boolean isAVLTree(IAVLNode node) {
        if (!node.isRealNode())
            return true;

        assert node.getHeight() == getNodeHeight(node);
        assert !node.getRight().isRealNode() || node.getRight().getKey() > node.getKey();
        assert !node.getLeft().isRealNode() || node.getLeft().getKey() < node.getKey();

        if (Math.abs(getNodeHeight(node.getLeft()) - getNodeHeight(node.getRight())) > 1)
            return false;
        return isAVLTree(node.getRight()) && isAVLTree(node.getLeft());
    }

    public boolean isAVLTree(AVLTree tree) {
        return isAVLTree(tree.getRoot());
    }

    @Test
    public void testInsert() {
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
    public void testDelete() {
        AVLTree tree = new AVLTree();

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 50);
            if (!numbers.contains(randomNum)) {
                tree.insert(randomNum, String.valueOf(randomNum));
                numbers.add(randomNum);
                assert isAVLTree(tree);
            }
        }

        for (int i = 0; i < numbers.size() - 1; i++) {
            //printTree(tree);
            int randomKeyIndex = ThreadLocalRandom.current().nextInt(0, numbers.size() - 1);
            int randomKey = numbers.get(randomKeyIndex);

            System.out.println("Before deleting " + randomKey);
            printTree(tree);
            tree.delete(randomKey);
            System.out.println("After deleting " + randomKey);
            printTree(tree);
            assert isAVLTree(tree);
            numbers.remove(randomKeyIndex);
        }
    }
}
