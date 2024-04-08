import java.util.ArrayList;

public class bst {
    private static class Node {
        int data;
        Node leftNode;
        Node rightNode;

        public Node(int data) {
            this.data = data;
        }
    }

    // inserting values in bst
    private static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (val < root.data) {
            root.leftNode = insert(root.leftNode, val);
        } else {
            root.rightNode = insert(root.rightNode, val);
        }

        return root;
    }

    // inorder traversal in binary search tree
    private static void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.leftNode);
        System.out.print(root.data + " ");
        inorder(root.rightNode);
    }

    // Find value in Binary search tree
    private static boolean bst(Node root, int val) {
        if (root == null) {
            return false;
        }

        if (root.data == val) {
            return true;
        }

        if (val < root.data) {
            return bst(root.leftNode, val);
        } else {
            return bst(root.rightNode, val);
        }
    }

    // deleting a node in bst
    private static Node delete(Node root, int val) {
        // search the node to be deleted
        if (val < root.data) {
            root.leftNode = delete(root.leftNode, val);
        } else if (val > root.data) {
            root.rightNode = delete(root.rightNode, val);
        } else { // root.data == val
                 // Case 1: node is leaf node
            if (root.leftNode == null && root.rightNode == null) {
                return null;
                // Case 2: node has 1 child
            } else if (root.leftNode == null) {
                return root.rightNode;
            } else if (root.rightNode == null) {
                return root.leftNode;
            }

            // Case 3: node has 2 child
            Node successor = inorderSuccessor(root.rightNode);
            root.data = successor.data;
            root.rightNode = delete(root.rightNode, successor.data);
        }

        return root;
    }

    // Function to find the inorder successor of the node
    private static Node inorderSuccessor(Node root) {
        while (root.leftNode != null) {
            root = root.leftNode;
        }
        return root;
    }

    // all nodes in range (X, Y) inclusive
    private static void printInRange(Node root, int X, int Y) {
        if (root == null) {
            return;
        }

        if (root.data >= X && root.data <= Y) {
            printInRange(root.leftNode, X, Y);
            System.out.print(root.data + " ");
            printInRange(root.rightNode, X, Y);
        } else if (root.data >= Y) {
            printInRange(root.leftNode, X, Y);
        } else {
            printInRange(root.rightNode, X, Y);
        }
    }

    // Root to leaf paths
    private static void printRoot2LeafPaths(Node root, ArrayList<Integer> path) {

        if (root == null) {
            return;
        }

        path.add(root.data);

        if (root.leftNode == null && root.rightNode == null) {
            System.out.println(path);
        } else {
            printRoot2LeafPaths(root.leftNode, path);
            printRoot2LeafPaths(root.rightNode, path);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {

        int[] values = { 8, 5, 3, 6, 10, 11, 14 };
        Node root = null;

        for (int val : values) {
            root = insert(root, val);
        }

        inorder(root);
        System.out.println();

        // delete(root, 5);
        inorder(root);

        System.out.println();

        printInRange(root, 5, 11);
        System.out.println();

        printRoot2LeafPaths(root, new ArrayList<Integer>());

    }

}
