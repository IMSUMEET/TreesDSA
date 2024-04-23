package BinaryTrees;

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
        private int idx = -1;

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

        private int heightOfTree(Node root) {
            if (root == null)
                return 0;

            return 1 + Math.max(heightOfTree(root.leftNode), heightOfTree(root.rightNode));
        }

        // snapdeal and adobe
        // O(n^2)
        private int diameterOfTree(Node root) {
            if (root == null)
                return 0;

            int leftSubtree = diameterOfTree(root.leftNode);
            int rightSubtree = diameterOfTree(root.rightNode);
            int diameterFromRoot = heightOfTree(root.leftNode) + heightOfTree(root.rightNode) + 1;

            return Math.max(Math.max(leftSubtree, rightSubtree), diameterFromRoot);
        }

        // O(n)
        // class to store TreeInfo
        private static class TreeInfo {
            int height;
            int diameter;

            public TreeInfo(int height, int diameter) {
                this.height = height;
                this.diameter = diameter;
            }

        }

        private TreeInfo diameterOfTreeOptimized(Node root) {
            if (root == null)
                return new TreeInfo(0, 0);

            TreeInfo leftInfo = diameterOfTreeOptimized(root.leftNode);
            TreeInfo rightInfo = diameterOfTreeOptimized(root.rightNode);

            int currheight = Math.max(leftInfo.height, rightInfo.height) + 1;
            int currdiameter = Math.max(Math.max(leftInfo.diameter, rightInfo.diameter),
                    leftInfo.height + rightInfo.height + 1);

            return new TreeInfo(currheight, currdiameter);

        }

        // checking if given tree a subTree of Main Tree
        private boolean isIdentical(Node root, Node subRoot) {

            if (root == null && subRoot == null)
                return true;

            if (root == null || subRoot == null)
                return false;

            if (root.data == subRoot.data) {
                return isIdentical(root.leftNode, subRoot.leftNode) && isIdentical(root.rightNode, subRoot.rightNode);
            }

            return false;
        }

        private boolean isSubtree(Node root, Node subRoot) {
            if (subRoot == null)
                return true;

            if (root == null)
                return false;

            if (root.data == subRoot.data) {
                if (isIdentical(root, subRoot)) {
                    return true;
                }
            }

            return isSubtree(root.leftNode, subRoot) || isSubtree(root.rightNode, subRoot);

        }

        // Sum of nodes at Level K (~ level order traversal)
        private int sumAtLevelK(Node root, int k) {
            if (root == null)
                return 0;

            if (k == 1) {
                return root.data;
            }

            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            queue.add(null);

            int sum = 0;
            int level = 1;
            while (!queue.isEmpty()) {
                Node top = queue.remove();

                if (top == null) {
                    level++;
                    if (level == k + 1) {
                        return sum;
                    }
                    sum = 0;
                    queue.add(null);
                } else {
                    sum += top.data;
                    if (top.leftNode != null)
                        queue.add(top.leftNode);
                    if (top.rightNode != null)
                        queue.add(top.rightNode);
                }
            }

            return sum;

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

        System.out.print("\n\nHeight of Tree\n");
        System.out.println(tree.heightOfTree(root));

        System.out.print("\n\nDiameter Of tree\n");
        System.out.println(tree.diameterOfTree(root));

        System.out.print("\n\nDiameter Of tree Optimized\n");
        System.out.println(tree.diameterOfTreeOptimized(root).diameter);

        int[] nodesSubtree = { 2, 4, -1, -1, 5, -1, -1 };
        BinaryTree subTree = new BinaryTree();
        Node subTreeRoot = subTree.buildTree(nodesSubtree);

        System.out.print("\n\nLevelOrder of Subtree\n");
        subTree.levelOrder(subTreeRoot);

        System.out.println("\n\nIs Subtree of tree\n");
        System.out.println(tree.isSubtree(root, subTreeRoot));

        System.out.println("\n\nSubOfNodes at level K\n");
        System.out.println(tree.sumAtLevelK(root, 1));

    }
}