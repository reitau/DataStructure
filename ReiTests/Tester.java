import org.junit.Assert;
import org.junit.Test;
import Tree.AVLTree;
import Tree.AVLTree.IAVLNode;

public class Tester {

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
  }

  @Test
  public void testMinMaxInsertDelete()  throws Exception {
    AVLTree tree = new AVLTree();
    int rebalance_operation = 0;

    tree.insert(2, "2");
    Assert.assertEquals("2", tree.max());
    Assert.assertEquals("2", tree.min());

    tree.insert(3, "3");
    Assert.assertEquals("3", tree.max());
    Assert.assertEquals("2", tree.min());

    tree.insert(1, "1");
    Assert.assertEquals("3", tree.max());
    Assert.assertEquals("1", tree.min());

    tree.insert(4, "4");
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("1", tree.min());

    tree.delete(2);
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("1", tree.min());

    tree.delete(2);
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("1", tree.min());

    tree.delete(1);
    Assert.assertEquals("4", tree.max());
    Assert.assertEquals("3", tree.min());

    tree.delete(4);
    Assert.assertEquals("3", tree.max());
    Assert.assertEquals("3", tree.min());
  }

}
