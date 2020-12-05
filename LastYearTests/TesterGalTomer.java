import java.util.Arrays;

public class TesterGalTomer {

    public static void main(String[] args) {
        System.out.println("Hey! The tester checks insert, deletes and key/values to array. \n" +
                "Each method prints the final tree (you can choose to print key, rank or size). \n" +
                "If you find any issues or other tests we didn’t check, let us know. \n" +
                "Thank, Gal Maimon and Tomer Amir  \n");
        AVLTree tree = createTree();
        checkSize();
        checkDeleteDemote();
        checkDeleteUnaryNoFix_deleteLeftLeft();
        checkDeleteUnaryNoFix_deleteLeftRight();
        checkDeleteUnaryNoFix_deleteRightRight();
        checkDeleteUnaryNoFix_deleteRightLeft();
        checkDeleteSingleRotation();
        checkDeleteSingleRotationSymmetric();
        checkDeleteDoubleRotationSymmetric();
        checkDeleteSingleRotationDemoteSymmetric();
        checkInsertDoubleRotationSymetric_case2();
        checkInsertDoubleRotationSymetric_case2();
        checkKeysToArray();
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        System.out.println("Tester is finished!");
    }

    public static void checkJoin() {
        AVLTree tree1 = new AVLTree();
        tree1.insert(5, "");
        tree1.insert(4, "");
        tree1.insert(20, "");
        tree1.insert(2, "");

        AVLTree tree2 = new AVLTree();
        tree2.insert(100, "");
        tree2.insert(200, "");
        tree2.insert(300, "");
        tree2.insert(150, "");

        AVLTree tree3 = new AVLTree();
        tree3.insert(60, "");

        tree1.join(tree3.getRoot(), tree2);
        BTreePrinter.printNode(tree1.getRoot(), "key");  // support "key", "size" and "rank"
        System.out.println("_________________________");
    }

    public static void checkDeleteUnaryNoFix_deleteLeftLeft() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(4, "");
        tree.insert(20, "");
        tree.insert(2, "");
        tree.insert(25, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank"
        System.out.println("_________________________");
        tree.delete(4);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteUnaryNoFix_deleteLeftRight() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(3, "");
        tree.insert(20, "");
        tree.insert(4, "");
        tree.insert(25, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(3);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }


    public static void checkDeleteUnaryNoFix_deleteRightRight() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(3, "");
        tree.insert(20, "");
        tree.insert(4, "");
        tree.insert(21, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(20);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteUnaryNoFix_deleteRightLeft() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(3, "");
        tree.insert(20, "");
        tree.insert(4, "");
        tree.insert(19, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(20);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteSingleRotation() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(4, "");
        tree.insert(20, "");
        tree.insert(3, "");
        tree.insert(50, "");
        tree.insert(10, "");
        ;
        tree.insert(15, "");
        tree.insert(60, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(4);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteDemote() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(4, "");
        tree.insert(20, "");
        tree.insert(3, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(4);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteSingleRotationSymmetric() {
        AVLTree tree = new AVLTree();
        tree.insert(10, "");
        tree.insert(5, "");
        tree.insert(20, "");
        tree.insert(3, "");
        tree.insert(7, "");
        tree.insert(50, "");
        ;
        tree.insert(1, "");
        tree.insert(8, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(20);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteDoubleRotationSymmetric() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(16, "");
        tree.insert(3, "");
        tree.insert(4, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(16);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteSingleRotationDemoteSymmetric() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(16, "");
        tree.insert(3, "");
        tree.insert(2, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(16);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteSingleRotationDemote() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(2, "");
        tree.insert(16, "");
        tree.insert(17, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(2);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkInsertDoubleRotationSymetric_case2() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(16, "");
        tree.insert(2, "");
        tree.insert(9, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkRoot() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(5);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDoubleRotationLikeInClass() { // see pre
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(3, "");
        tree.insert(4, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDoubleRotationSymmetricClass() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "");
        tree.insert(7, "");
        tree.insert(6, "");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteFatherWithTwoSons() {
        AVLTree tree = createTree();
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(12);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkDeleteFatherWithTwoSons_case2() {
        AVLTree tree = createTree();
        tree.insert(15, "ok");
        tree.insert(13, "ok");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        tree.delete(12);
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void scenariosForInsert() {
        AVLTree tree = new AVLTree();
        tree.insert(5, ""); //checks insert to root
        tree.insert(12, ""); //checks insert to leaf (right)
        tree.insert(14, ""); // checks single rotation
        tree.insert(2, ""); // checks insert to leaf (left)
        tree.insert(9, ""); //checks insert to unary (right)
        tree.insert(1, ""); //checks insert single rotation
        tree.insert(6, ""); // checks double rotation
//                BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
//        System.out.println("_________________________");
    }

    public static void checkSize() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "pizza");
        tree.insert(3, "pizza");
        tree.insert(12, "pizza");
        tree.insert(1, "pizza");
        tree.insert(16, "pizza");
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static AVLTree createTree() {
        AVLTree tree = new AVLTree();
        tree.insert(5, "pizza"); //checks insert to root
        tree.insert(12, "olive"); //checks insert to leaf (right)
        tree.insert(14, "pizza"); // checks single rotation
        tree.insert(2, "mozzarela"); // checks insert to leaf (left)
        tree.insert(9, "good"); //checks insert to unary (right)
        tree.insert(1, "good"); //checks insert single rotation
//        tree.insert(6,""); // checks double rotation
        //         BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        //  System.out.println("_________________________");
        return tree;
    }

    public static void checkDelete() {
        AVLTree tree = createTree();
        // no Rotations
        tree.delete(1); //delete left leaf
        tree.delete(14); //delete right leaf
        tree.insert(16, "ok");// add for the next delete
        tree.delete(12); //delete parent with 2 sons
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkUnary() {
        AVLTree tree = createTree();
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
    }

    public static void checkKeysToArray() {
        AVLTree tree = createTree();
        BTreePrinter.printNode(tree.getRoot(), "key");  // support "key", "size" and "rank";
        System.out.println("_________________________");
        String[] array = tree.infoToArray();
        System.out.println(Arrays.toString(array));
    }

}
