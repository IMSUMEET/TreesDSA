import java.util.LinkedList;
import java.util.Queue;

public class Trees {
    static class Node {
        int data;
        Node leftNode;
        Node rightNode;

        public Node(int data) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        private Node buildTree(int[] nodes) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.leftNode = buildTree(nodes);
            newNode.rightNode = buildTree(nodes);

            return newNode;
        }

        private void inOrder(Node root) {
            if (root == null) {
                return;
            }

            inOrder(root.leftNode);
            System.out.print(root.data + " ");
            inOrder(root.rightNode);
        }

        private void preOrder(Node root) {
            if (root == null) {
                return;
            }

            System.out.print(root.data + " ");
            preOrder(root.leftNode);
            preOrder(root.rightNode);
        }

        private void postOrder(Node root) {
            if (root == null) {
                return;
            }

            postOrder(root.leftNode);
            postOrder(root.rightNode);
            System.out.print(root.data + " ");
        }

        private void levelOrder(Node root) {

            if (root == null)
                return;

            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            queue.add(null);

            while (!queue.isEmpty()) {
                Node top = queue.remove();
                if (top == null) {
                    System.out.println();
                    if (queue.isEmpty()) {
                        break;
                    } else {
                        queue.add(null);
                    }
                } else {
                    System.out.print(top.data);

                    if (top.leftNode != null) {
                        queue.add(top.leftNode);
                    }

                    if (top.rightNode != null) {
                        queue.add(top.rightNode);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // -1 represents null values (leaf nodes) (given nodes are in preorder fashion)
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);

        System.out.println("Inorder");
        tree.inOrder(root);

        System.out.println("\n\nPreorder");
        tree.preOrder(root);

        System.out.println("\n\nPostOrder");
        tree.postOrder(root);

        System.out.print("\n\nLevelOrder\n");
        tree.levelOrder(root);
    }
}