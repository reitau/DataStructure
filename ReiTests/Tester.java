import org.junit.Assert;
import org.junit.Test;
import Tree.AVLTree;
import Tree.AVLTree.IAVLNode;

public class Tester {
  private boolean inner_isBSTTree(IAVLNode node, int max, int min) {
    if(node == null || !node.isRealNode()) {
      return true;
    }
    if(node.getKey() < min || node.getKey() > max) {
      return false;
    }
    if(!(node.getRight().isRealNode() || node.getRight().isRealNode())) {
      return true;
    }
    return inner_isBSTTree(node.getLeft(), node.getKey(), min) && inner_isBSTTree(node.getRight(), max, node.getKey());
  }

  private boolean isBSTTree(AVLTree tree) {
    return inner_isBSTTree(tree.getRoot(), Integer.MAX_VALUE, -1);
  }

  @Test
  public void testHigh()  throws Exception {
    AVLTree tree = new AVLTree();
    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(3, "3");
    tree.insert(4, "4");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_3 = node_2.getRight();
    IAVLNode node_4 = node_3.getRight();


    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(3, node_3.getKey());
    Assert.assertEquals(4, node_4.getKey());


    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(1, node_3.getHeight());
    Assert.assertEquals(0, node_4.getHeight());

    Assert.assertTrue(isBSTTree(tree));
  }


  @Test
  public void testSingleRotateRight()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    rebalance_operation = tree.insert(3, "3");
    Assert.assertEquals(0, rebalance_operation);

    rebalance_operation = tree.insert(2, "2");
    Assert.assertEquals(1, rebalance_operation);

    rebalance_operation = tree.insert(1, "1");
    Assert.assertEquals(3, rebalance_operation);

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_3 = node_2.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());

    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_2.getHeight());
    Assert.assertEquals(0, node_3.getHeight());

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testSingleRotateLeft()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    rebalance_operation = tree.insert(1, "1");
    Assert.assertEquals(0, rebalance_operation);

    rebalance_operation = tree.insert(2, "2");
    Assert.assertEquals(1, rebalance_operation);

    rebalance_operation = tree.insert(3, "3");
    Assert.assertEquals(3, rebalance_operation);

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_3 = node_2.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());

    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_2.getHeight());
    Assert.assertEquals(0, node_3.getHeight());

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDoubleRotateLR()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    rebalance_operation = tree.insert(3, "3");
    Assert.assertEquals(0, rebalance_operation);

    rebalance_operation = tree.insert(1, "1");
    Assert.assertEquals(1, rebalance_operation);

    rebalance_operation = tree.insert(2, "2");
    Assert.assertEquals(6, rebalance_operation);

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_3 = node_2.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());

    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_2.getHeight());
    Assert.assertEquals(0, node_3.getHeight());

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDoubleRotateRL()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    rebalance_operation = tree.insert(1, "1");
    Assert.assertEquals(0, rebalance_operation);

    rebalance_operation = tree.insert(3, "3");
    Assert.assertEquals(1, rebalance_operation);

    rebalance_operation = tree.insert(2, "2");
    Assert.assertEquals(6, rebalance_operation);

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_3 = node_2.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());

    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_2.getHeight());
    Assert.assertEquals(0, node_3.getHeight());

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteLeafDemote()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(1, "1");
    tree.insert(3, "3");
    tree.insert(2, "2");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_3 = node_2.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());

    rebalance_operation = tree.delete(node_1.getKey());
    Assert.assertFalse(node_2.getLeft().isRealNode());
    Assert.assertEquals(0, rebalance_operation);

    rebalance_operation = tree.delete(node_3.getKey());
    Assert.assertFalse(node_2.getRight().isRealNode());
    Assert.assertEquals(1, rebalance_operation);

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteSingleRotateALeft()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(4, "4");
    tree.insert(3, "3");
    tree.insert(5, "5");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_4 = node_2.getRight();
    IAVLNode node_3 = node_4.getLeft();
    IAVLNode node_5 = node_4.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());
    Assert.assertEquals(4, node_4.getKey());
    Assert.assertEquals(5, node_5.getKey());

    rebalance_operation = tree.delete(node_1.getKey());
    Assert.assertEquals(null, tree.search(1));
    Assert.assertEquals(4, tree.getRoot().getKey());
    Assert.assertEquals(2, tree.getRoot().getHeight());
    Assert.assertEquals(2, tree.getRoot().getLeft().getKey());
    Assert.assertEquals(5, tree.getRoot().getRight().getKey());
    Assert.assertEquals(3, tree.getRoot().getLeft().getRight().getKey());
    Assert.assertEquals(3, rebalance_operation);

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteSingleRotateARight()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(4, "4");
    tree.insert(5, "5");
    tree.insert(2, "2");
    tree.insert(3, "3");
    tree.insert(1, "1");

    IAVLNode node_4 = tree.getRoot();
    IAVLNode node_2 = node_4.getLeft();
    IAVLNode node_5 = node_4.getRight();
    IAVLNode node_3 = node_2.getRight();
    IAVLNode node_1 = node_2.getLeft();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, node_3.getKey());
    Assert.assertEquals(4, node_4.getKey());
    Assert.assertEquals(5, node_5.getKey());

    rebalance_operation = tree.delete(node_5.getKey());
    Assert.assertEquals(null, tree.search(5));
    Assert.assertEquals(2, tree.getRoot().getKey());
    Assert.assertEquals(2, tree.getRoot().getHeight());
    Assert.assertEquals(1, tree.getRoot().getLeft().getKey());
    Assert.assertEquals(4, tree.getRoot().getRight().getKey());
    Assert.assertEquals(3, tree.getRoot().getRight().getLeft().getKey());
    Assert.assertEquals(3, rebalance_operation);

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteSingleRotateBLeft()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(4, "4");
    tree.insert(5, "5");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_4 = node_2.getRight();
    IAVLNode node_5 = node_4.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(4, node_4.getKey());
    Assert.assertEquals(5, node_5.getKey());

    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_4.getHeight());

    rebalance_operation = tree.delete(node_1.getKey());
    Assert.assertEquals(null, tree.search(1));
    Assert.assertEquals(4, tree.getRoot().getKey());
    Assert.assertEquals(1, tree.getRoot().getHeight());
    Assert.assertEquals(2, tree.getRoot().getLeft().getKey());
    Assert.assertEquals(5, tree.getRoot().getRight().getKey());
    Assert.assertEquals(3, rebalance_operation);

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteDoubleRotateLeft()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(4, "4");
    tree.insert(3, "3");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_4 = node_2.getRight();
    IAVLNode node_3 = node_4.getLeft();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(4, node_4.getKey());
    Assert.assertEquals(3, node_3.getKey());

    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_4.getHeight());

    rebalance_operation = tree.delete(node_1.getKey());
    Assert.assertEquals(null, tree.search(1));
    Assert.assertEquals(3, tree.getRoot().getKey());
    Assert.assertEquals(1, tree.getRoot().getHeight());
    Assert.assertEquals(2, tree.getRoot().getLeft().getKey());
    Assert.assertEquals(4, tree.getRoot().getRight().getKey());
    Assert.assertEquals(0, tree.getRoot().getLeft().getHeight());
    Assert.assertEquals(0, tree.getRoot().getRight().getHeight());
    Assert.assertEquals(6, rebalance_operation);

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteNodeWithTwoChildren()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(4, "4");
    tree.insert(3, "3");
    tree.insert(5, "5");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_4 = node_2.getRight();
    IAVLNode node_3 = node_4.getLeft();
    IAVLNode node_5 = node_4.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(4, node_4.getKey());
    Assert.assertEquals(3, node_3.getKey());
    Assert.assertEquals(5, node_5.getKey());

    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_4.getHeight());

    rebalance_operation = tree.delete(node_4.getKey());
    Assert.assertEquals(null, tree.search(4));

    Assert.assertEquals(2, tree.getRoot().getKey());
    Assert.assertEquals(2, tree.getRoot().getHeight());
    Assert.assertEquals(1, tree.getRoot().getLeft().getKey());
    Assert.assertEquals(5, tree.getRoot().getRight().getKey());
    Assert.assertEquals(0, tree.getRoot().getLeft().getHeight());
    Assert.assertEquals(1, tree.getRoot().getRight().getHeight());
    Assert.assertEquals(0, rebalance_operation);

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testDeleteRoots()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(4, "4");
    tree.insert(3, "3");
    tree.insert(5, "5");

    IAVLNode node_2 = tree.getRoot();
    IAVLNode node_1 = node_2.getLeft();
    IAVLNode node_4 = node_2.getRight();
    IAVLNode node_3 = node_4.getLeft();
    IAVLNode node_5 = node_4.getRight();

    Assert.assertEquals(1, node_1.getKey());
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(4, node_4.getKey());
    Assert.assertEquals(3, node_3.getKey());
    Assert.assertEquals(5, node_5.getKey());

    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(0, node_1.getHeight());
    Assert.assertEquals(1, node_4.getHeight());

    rebalance_operation = tree.delete(node_2.getKey());
    Assert.assertEquals(null, tree.search(2));
    Assert.assertEquals(3, tree.getRoot().getKey());
    Assert.assertEquals(0, rebalance_operation);
    Assert.assertTrue(isBSTTree(tree));

    rebalance_operation = tree.delete(node_3.getKey());
    Assert.assertEquals(null, tree.search(3));
    Assert.assertEquals(4, tree.getRoot().getKey());
    Assert.assertEquals(1, rebalance_operation);
    Assert.assertTrue(isBSTTree(tree));

    rebalance_operation = tree.delete(node_4.getKey());
    Assert.assertEquals(null, tree.search(4));
    Assert.assertEquals(5, tree.getRoot().getKey());
    Assert.assertEquals(0, rebalance_operation);
    Assert.assertTrue(isBSTTree(tree));

    rebalance_operation = tree.delete(node_5.getKey());
    Assert.assertEquals(null, tree.search(5));
    Assert.assertEquals(1, tree.getRoot().getKey());
    Assert.assertEquals(0, rebalance_operation);
    Assert.assertTrue(isBSTTree(tree));

    rebalance_operation = tree.delete(node_1.getKey());
    Assert.assertEquals(null, tree.search(1));
    Assert.assertEquals(null, tree.getRoot());
    Assert.assertEquals(0, rebalance_operation);
    Assert.assertTrue(isBSTTree(tree));

    Assert.assertTrue(tree.empty());
  }

  @Test
  public void testMinMaxInsertDelete()  throws Exception {
    AVLTree tree = new AVLTree();
    Assert.assertNull(null, tree.max());
    Assert.assertNull(null, tree.min());

    tree.insert(2, "2");
    Assert.assertEquals("2", tree.max());
    Assert.assertEquals("2", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.insert(3, "3");
    Assert.assertEquals("3", tree.max());
    Assert.assertEquals("2", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.insert(1, "1");
    Assert.assertEquals("3", tree.max());
    Assert.assertEquals("1", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.insert(4, "4");
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("1", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.delete(2);
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("1", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.delete(2);
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("1", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.delete(1);
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("3", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.delete(4);
    Assert.assertEquals("3", tree.max());
    Assert.assertEquals("3", tree.min());
    Assert.assertTrue(isBSTTree(tree));

    tree.delete(3);
    Assert.assertNull(null, tree.max());
    Assert.assertNull(null, tree.min());
  }

  @Test
  public void testJoinSmallerRankIsGreater() throws Exception {
    AVLTree smaller = new AVLTree();
    AVLTree bigger = new AVLTree();

    AVLTree i_hate_java = new AVLTree();
    i_hate_java.insert(10, "10");
    IAVLNode x = i_hate_java.getRoot();

    smaller.insert(2, "2");
    smaller.insert(1, "1");
    smaller.insert(4, "4");
    smaller.insert(3, "3");
    smaller.insert(5, "5");

    bigger.insert(12, "12");
    bigger.insert(11, "11");
    bigger.insert(13, "13");

    int complexity = smaller.join(x, bigger);
    IAVLNode node_10 = smaller.getRoot();
    Assert.assertEquals(10, node_10.getKey());
    Assert.assertEquals(2, complexity);

    Assert.assertEquals("13", smaller.max());
    Assert.assertEquals("1", smaller.min());

    Assert.assertNotNull(smaller.search(12));
    Assert.assertNotNull(smaller.search(4));
    Assert.assertNotNull(smaller.search(10));

    Assert.assertEquals(3, node_10.getHeight());
    Assert.assertEquals(2, node_10.getLeft().getKey());
    Assert.assertEquals(12, node_10.getRight().getKey());

    Assert.assertTrue(isBSTTree(smaller));
  }

  @Test
  public void testJoinBiggerRankIsSmaller() throws Exception {
    AVLTree smaller = new AVLTree();
    AVLTree bigger = new AVLTree();

    AVLTree i_hate_java = new AVLTree();
    i_hate_java.insert(10, "10");
    IAVLNode x = i_hate_java.getRoot();

    smaller.insert(2, "2");
    smaller.insert(1, "1");
    smaller.insert(4, "4");
    smaller.insert(3, "3");
    smaller.insert(5, "5");

    bigger.insert(12, "12");
    bigger.insert(11, "11");
    bigger.insert(13, "13");

    int complexity = bigger.join(x, smaller);
    IAVLNode node_10 = bigger.getRoot();
    Assert.assertEquals(10, node_10.getKey());
    Assert.assertEquals(2, complexity);

    Assert.assertEquals("13", bigger.max());
    Assert.assertEquals("1", bigger.min());

    Assert.assertNotNull(bigger.search(12));
    Assert.assertNotNull(bigger.search(4));
    Assert.assertNotNull(bigger.search(1));
    Assert.assertNotNull(bigger.search(10));

    Assert.assertEquals(3, node_10.getHeight());
    Assert.assertEquals(2, node_10.getLeft().getKey());
    Assert.assertEquals(12, node_10.getRight().getKey());

    Assert.assertTrue(isBSTTree(bigger));
  }

  @Test
  public void testJoinSmallerRankIsSmaller() throws Exception {
    AVLTree smaller = new AVLTree();
    AVLTree bigger = new AVLTree();

    AVLTree i_hate_java = new AVLTree();
    i_hate_java.insert(10, "10");
    IAVLNode x = i_hate_java.getRoot();

    smaller.insert(2, "2");
    smaller.insert(1, "1");
    smaller.insert(3, "3");

    bigger.insert(12, "12");
    bigger.insert(11, "11");
    bigger.insert(14, "14");
    bigger.insert(13, "13");
    bigger.insert(15, "15");

    int complexity = smaller.join(x, bigger);
    IAVLNode node_12 = smaller.getRoot();
    Assert.assertEquals(12, node_12.getKey());
    Assert.assertEquals(2, complexity);

    Assert.assertEquals("15", smaller.max());
    Assert.assertEquals("1", smaller.min());

    Assert.assertNotNull(smaller.search(11));
    Assert.assertNotNull(smaller.search(3));
    Assert.assertNotNull(smaller.search(10));

    Assert.assertEquals(3, node_12.getHeight());
    Assert.assertEquals(10, node_12.getLeft().getKey());
    Assert.assertEquals(14, node_12.getRight().getKey());

    Assert.assertTrue(isBSTTree(smaller));
  }

  @Test
  public void testJoinBiggerRankIsBigger() throws Exception {
    AVLTree smaller = new AVLTree();
    AVLTree bigger = new AVLTree();

    AVLTree i_hate_java = new AVLTree();
    i_hate_java.insert(10, "10");
    IAVLNode x = i_hate_java.getRoot();

    smaller.insert(2, "2");
    smaller.insert(1, "1");
    smaller.insert(3, "3");

    bigger.insert(12, "12");
    bigger.insert(11, "11");
    bigger.insert(14, "14");
    bigger.insert(13, "13");
    bigger.insert(15, "15");

    int complexity = bigger.join(x, smaller);
    IAVLNode node_12 = bigger.getRoot();
    Assert.assertEquals(12, node_12.getKey());
    Assert.assertEquals(2, complexity);

    Assert.assertEquals("15", bigger.max());
    Assert.assertEquals("1", bigger.min());

    Assert.assertNotNull(bigger.search(2));
    Assert.assertNotNull(bigger.search(15));
    Assert.assertNotNull(bigger.search(3));
    Assert.assertNotNull(bigger.search(10));

    Assert.assertEquals(3, node_12.getHeight());
    Assert.assertEquals(10, node_12.getLeft().getKey());
    Assert.assertEquals(14, node_12.getRight().getKey());

    Assert.assertTrue(isBSTTree(bigger));
  }

  @Test
  public void testJoinEmpty() throws Exception {
    AVLTree tree = new AVLTree();
    AVLTree empty = new AVLTree();

    AVLTree i_hate_java = new AVLTree();
    i_hate_java.insert(10, "10");
    IAVLNode x = i_hate_java.getRoot();

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(3, "3");

    int complexity = tree.join(x, empty);
    IAVLNode node_2 = tree.getRoot();
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, complexity);

    Assert.assertEquals("10", tree.max());
    Assert.assertEquals("1", tree.min());

    Assert.assertNotNull(tree.search(2));

    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(1, node_2.getLeft().getKey());
    Assert.assertEquals(3, node_2.getRight().getKey());

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testJoinEmpty2() throws Exception {
    AVLTree tree = new AVLTree();
    AVLTree empty = new AVLTree();

    AVLTree i_hate_java = new AVLTree();
    i_hate_java.insert(10, "10");
    IAVLNode x = i_hate_java.getRoot();

    tree.insert(2, "2");
    tree.insert(1, "1");
    tree.insert(3, "3");

    int complexity = empty.join(x, tree);
    IAVLNode node_2 = empty.getRoot();
    Assert.assertEquals(2, node_2.getKey());
    Assert.assertEquals(3, complexity);

    Assert.assertEquals("10", empty.max());
    Assert.assertEquals("1", empty.min());

    Assert.assertNotNull(empty.search(2));

    Assert.assertEquals(2, node_2.getHeight());
    Assert.assertEquals(1, node_2.getLeft().getKey());
    Assert.assertEquals(3, node_2.getRight().getKey());

    Assert.assertTrue(isBSTTree(tree));
  }

  @Test
  public void testSplitBigTree12() throws Exception {
    int x_key = 12;
    AVLTree tree = new AVLTree();

    for(int i=0; i < 50; i++) {
      tree.insert(i, Integer.toString(i));
      Assert.assertTrue(isBSTTree(tree));
    }

    AVLTree[] trees = tree.split(x_key);
    Assert.assertEquals(2, trees.length);
    Assert.assertTrue(isBSTTree(trees[0]));
    Assert.assertTrue(isBSTTree(trees[1]));

    Assert.assertTrue(Integer.parseInt(trees[0].max()) < x_key);
    Assert.assertTrue(Integer.parseInt(trees[1].min()) > x_key);

    Assert.assertNull(trees[0].search(x_key));
    Assert.assertNull(trees[1].search(x_key));
  }

  @Test
  public void testSplitBigTree49() throws Exception {
    int x_key = 49;
    AVLTree tree = new AVLTree();

    for(int i=0; i < 50; i++) {
      tree.insert(i, Integer.toString(i));
      Assert.assertTrue(isBSTTree(tree));
    }

    AVLTree[] trees = tree.split(x_key);
    Assert.assertEquals(2, trees.length);
    Assert.assertTrue(isBSTTree(trees[0]));
    Assert.assertTrue(isBSTTree(trees[1]));

    Assert.assertTrue(Integer.parseInt(trees[0].max()) < x_key);
    Assert.assertTrue(trees[1].empty());

    Assert.assertNull(trees[0].search(x_key));
    Assert.assertNull(trees[1].search(x_key));
  }

  @Test
  public void testSplitBigTree1() throws Exception {
    int x_key = 1;
    AVLTree tree = new AVLTree();

    for(int i=0; i < 50; i++) {
      tree.insert(i, Integer.toString(i));
      Assert.assertTrue(isBSTTree(tree));
    }

    AVLTree[] trees = tree.split(x_key);
    Assert.assertEquals(2, trees.length);
    Assert.assertTrue(isBSTTree(trees[0]));
    Assert.assertTrue(isBSTTree(trees[1]));

    Assert.assertTrue(Integer.parseInt(trees[0].max()) < x_key);
    Assert.assertTrue(Integer.parseInt(trees[1].min()) > x_key);

    Assert.assertNull(trees[0].search(x_key));
    Assert.assertNull(trees[1].search(x_key));
  }

}
