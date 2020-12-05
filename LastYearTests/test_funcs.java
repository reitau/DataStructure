private static void printTree(AVLTree tree) {
    AVLTree.IAVLNode root = tree.getRoot();
    List<List<String>> lines = new ArrayList<List<String>>();

    List<AVLTree.IAVLNode> level = new ArrayList<AVLTree.IAVLNode>();
    List<AVLTree.IAVLNode> next = new ArrayList<AVLTree.IAVLNode>();

    level.add(root);
    int nn = 1;

    int widest = 0;

    while (nn != 0) {
        List<String> line = new ArrayList<String>();

        nn = 0;

        for (AVLTree.IAVLNode n : level) {
            if (n == null) {
                line.add(null);

                next.add(null);
                next.add(null);
            } else if (!n.isRealNode()) {
                line.add("*");
                next.add(null);
                next.add(null);
                nn++;
            } else {
                String aa = Integer.toString(n.getKey());
                line.add(aa);
                if (aa.length() > widest) widest = aa.length();

                next.add(n.getLeft());
                next.add(n.getRight());

                if (n.getLeft() != null) nn++;
                if (n.getRight() != null) nn++;
            }
        }

        if (widest % 8 == 1) widest++;

        lines.add(line);

        List<AVLTree.IAVLNode> tmp = level;
        level = next;
        next = tmp;
        next.clear();
    }

    int perpiece = lines.get(lines.size() - 2).size() * (widest);
    for (int i = 0; i < lines.size(); i++) {
        List<String> line = lines.get(i);
        int hpw = (int) Math.floor(perpiece / 2f) - 1;

        if (i > 0) {
            for (int j = 0; j < line.size(); j++) {

                // split node
                char c = ' ';
                if (j % 2 == 1) {
                    if (line.get(j - 1) != null) {
                        c = (line.get(j) != null) ? '┴' : '┘';
                    } else {
                        if (j < line.size() && line.get(j) != null) c = '└';
                    }
                }
                System.out.print(c);

                // lines and spaces
                if (line.get(j) == null) {
                    for (int k = 0; k < perpiece - 1; k++) {
                        System.out.print(" ");
                    }
                } else {

                    for (int k = 0; k < hpw; k++) {
                        System.out.print(j % 2 == 0 ? " " : "─");
                    }
                    System.out.print(j % 2 == 0 ? "┌" : "┐");
                    for (int k = 0; k < hpw; k++) {
                        System.out.print(j % 2 == 0 ? "─" : " ");
                    }
                }
            }
            System.out.println();
        }

        // print line of numbers
        for (int j = 0; j < line.size(); j++) {

            String f = line.get(j);
            if (f == null) f = "";
            int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
            int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

            // a number
            for (int k = 0; k < gap1; k++) {
                System.out.print(" ");
            }
            System.out.print(f);
            for (int k = 0; k < gap2; k++) {
                System.out.print(" ");
            }
        }
        System.out.println();

        perpiece /= 2;
    }
}

private static boolean isValidAvlRec(AVLTree.IAVLNode node, int min, int max) {
    // An empty tree is BST
    if (node == null || !node.isRealNode()) {
        return true;
    }

    // false if this node violates the min/max constraints
    int nodeKey = node.getKey();
    if ((nodeKey < min) || (nodeKey > max)) {
        return false;
    }

    if ((node.getHeight() == 0) && (node.getLeft().isRealNode() || node.getRight().isRealNode())) {
        // Sanity check
        return false;
    }
    if (Math.abs((node.getLeft().getHeight() - node.getRight().getHeight())) > 1) {
        return false;
    }
    int leftDiff = node.getHeight() - node.getLeft().getHeight();
    int rightDiff = node.getHeight() - node.getRight().getHeight();
    boolean is_1_1 = (leftDiff == 1) && (rightDiff == 1);
    boolean is_1_2 = (leftDiff == 1) && (rightDiff == 2);
    boolean is_2_1 = (leftDiff == 2) && (rightDiff == 1);
    if (!is_1_1 && !is_1_2 && !is_2_1) {
        return false;
    }

    // Otherwise check the subtrees recursively tightening the min/max constraints
    // Allow only distinct values
    return (isValidAvlRec(node.getLeft(), min, nodeKey - 1) &&
            isValidAvlRec(node.getRight(), nodeKey + 1, max));
}

private static int[] stringArrayToIntArray(String[] array) {
    int[] result = new int[array.length];
    for (int i = 0; i < result.length; i++) {
        result[i] = Integer.parseInt(array[i]);
    }
    return result;
}

private static boolean isSorted(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
        if (array[i] > array[i + 1]) {
            return false;
        }
    }

    return true;
}

private static boolean isValidAvlTree(AVLTree tree) {
    // Test infoToArray and keysToArray
    // Assumes the info fields match the keys
    int[] keysArr = tree.keysToArray();
    if (!isSorted(keysArr)) {
        return false;
    }
    if (!Arrays.equals(keysArr, stringArrayToIntArray(tree.infoToArray()))) {
        return false;
    }

    if (tree.getRoot().getParent() != null) {
        // Sanity check
        return false;
    }
    return isValidAvlRec(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
}