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

}
