import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BTreePrinter {

    public static void printNode(AVLTree.IAVLNode root) {
        printNode(root, "key");
    }

    public static void printNode(AVLTree.IAVLNode root, String printMode) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel, printMode);
    }

    private static void printNodeInternal(List<AVLTree.IAVLNode> nodes, int level, int maxLevel, String printMode) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<AVLTree.IAVLNode> newNodes = new ArrayList<AVLTree.IAVLNode>();
        for (AVLTree.IAVLNode node : nodes) {
            if (node != null) {
                String valToPrint;
                switch (printMode) {
                    case "key":
                        valToPrint = Integer.toString(node.getKey());
                        break;
                    case "height":
                        valToPrint = Integer.toString(node.getHeight());
                        break;
                    case "size":
                        valToPrint = Integer.toString(((AVLTree.AVLNode)node).getSize());
                        break;
                    default:
                        valToPrint = node.getValue();
                }
                System.out.print(valToPrint);
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel, printMode);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(AVLTree.IAVLNode node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.getLeft()), BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}