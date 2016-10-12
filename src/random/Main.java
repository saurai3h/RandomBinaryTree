package random;

import java.io.IOException;

import static java.lang.Math.random;

//generate a random tree of 1 or more nodes.
public class Main {

    private static final int MAX_VALUE = 1000000;
//    private static final int MAX_VALUE = 100;
//    private static final int MAX_NODES = 100000;
//    private static final int MAX_NODES = 10;
    private static final int MAX_LABEL = 1073741823;
//    private static final int MAX_LABEL = 107;



    public static void main(String[] args) throws IOException {

        int MAX_NODES = Integer.parseInt(args[0]);

        MyInt numberOfNodes = new MyInt((int)(random()*MAX_NODES));
        if(numberOfNodes.get() == 0)numberOfNodes.set(1);
        MyInt nodesMade = new MyInt(0);
        Node root = new Node();
        root.index = 1;
        root.value = (int)(random()*MAX_VALUE);
        nodesMade.incr();
        while (nodesMade.get() < numberOfNodes.get()) {
            createTree(root, numberOfNodes, nodesMade);
        }
        System.out.println(numberOfNodes.get());
        printTestCase(root);
    }

    private static void printTestCase(Node root)    {
        if(root != null)    {
            printTestCase(root.left);
            System.out.println(root.index + " " + root.value);
            printTestCase(root.right);
        }
    }
//    private static void printTree(Node root)    {
//        BTreePrinter.printNode(root);
//    }

    private static void createTree(Node nd, MyInt n, MyInt yet)   {
        if (yet.get() < n.get()) {
            int decision = (int)(random()*3);
//            create left child.
            if(decision == 0)   {
                if(nd.index * 2 >= MAX_LABEL)return;
                if(nd.left == null) {
                    Node left = new Node();
                    left.value = (int) (random() * MAX_VALUE);
                    left.index = nd.index * 2;
                    left.parent = nd;
                    nd.left = left;
                    yet.incr();
                }
                createTree(nd.left, n, yet);
            }
//            create right child.
            else if(decision == 1) {
                if(nd.index * 2 + 1 >= MAX_LABEL)return;
                if(nd.right == null) {
                    Node right = new Node();
                    right.value = (int) (random() * MAX_VALUE);
                    right.index = nd.index * 2 + 1;
                    right.parent = nd;
                    nd.right = right;
                    yet.incr();
                }
                createTree(nd.right, n, yet);
            }
//            create both children.
            else   {
                if(yet.get() >= n.get() - 1)createTree(nd, n, yet);

                if(nd.index * 2 >= MAX_LABEL)return;
                if(nd.left == null) {
                    Node left = new Node();
                    left.value = (int) (random() * MAX_VALUE);
                    left.index = nd.index * 2;
                    left.parent = nd;
                    nd.left = left;
                    yet.incr();
                }

                if(nd.index * 2 + 1 >= MAX_LABEL)return;
                if(nd.right == null) {
                    Node right = new Node();
                    right.value = (int) (random() * MAX_VALUE);
                    right.index = nd.index * 2 + 1;
                    right.parent = nd;
                    nd.right = right;
                    yet.incr();
                }

                if((int)(random()*2) == 0)  {
                    createTree(nd.left, n, yet);
                }else   {
                    createTree(nd.right,n, yet);
                }
            }
        }
    }
}

class MyInt {
    private int x;

    MyInt(int x)    {
        set(x);
    }
    void incr()    {
        ++this.x;
    }
    void set(int x)    {
        this.x = x;
    }
    int get()  {
        return this.x;
    }
}

class Node  {
    int value;
    int index;
    Node left;
    Node right;
    Node parent;
}

//class BTreePrinter {
//
//    public static <T extends Comparable<?>> void printNode(Node root) {
//        int maxLevel = BTreePrinter.maxLevel(root);
//
//        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
//    }
//
//    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
//        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
//            return;
//
//        int floor = maxLevel - level;
//        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
//        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
//        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
//
//        BTreePrinter.printWhitespaces(firstSpaces);
//
//        List<Node> newNodes = new ArrayList<Node>();
//        for (Node node : nodes) {
//            if (node != null) {
//                System.out.print(node.value);
//                newNodes.add(node.left);
//                newNodes.add(node.right);
//            } else {
//                newNodes.add(null);
//                newNodes.add(null);
//                System.out.print(" ");
//            }
//
//            BTreePrinter.printWhitespaces(betweenSpaces);
//        }
//        System.out.println("");
//
//        for (int i = 1; i <= endgeLines; i++) {
//            for (int j = 0; j < nodes.size(); j++) {
//                BTreePrinter.printWhitespaces(firstSpaces - i);
//                if (nodes.get(j) == null) {
//                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
//                    continue;
//                }
//
//                if (nodes.get(j).left != null)
//                    System.out.print("/");
//                else
//                    BTreePrinter.printWhitespaces(1);
//
//                BTreePrinter.printWhitespaces(i + i - 1);
//
//                if (nodes.get(j).right != null)
//                    System.out.print("\\");
//                else
//                    BTreePrinter.printWhitespaces(1);
//
//                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
//            }
//
//            System.out.println("");
//        }
//
//        printNodeInternal(newNodes, level + 1, maxLevel);
//    }
//
//    private static void printWhitespaces(int count) {
//        for (int i = 0; i < count; i++)
//            System.out.print(" ");
//    }
//
//    private static <T extends Comparable<?>> int maxLevel(Node node) {
//        if (node == null)
//            return 0;
//
//        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
//    }
//
//    private static <T> boolean isAllElementsNull(List<T> list) {
//        for (Object object : list) {
//            if (object != null)
//                return false;
//        }
//
//        return true;
//    }

//}