package ng.com.asciidev.trees;

public class BinarySearchTree <T extends Comparable<T>> {
    private int nodeCount = 0; // number of nodes in BST
    private Node root = null;

    private class Node {
        T data;
        Node left, right;
        public Node(Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Check if this binary tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // BST node count
    public int size() {
        return nodeCount;
    }

    // Add an element to this bst.
    public boolean add(T data) {
        // Check if the value already exists
        if (contains(data))
            return false;
        root = add(root, data);
        nodeCount++;
        return true;
    }

    private Node add(Node node, T data) {
        if (node == null) { // found a leaf node
            node = new Node(null, null, data);
        } else {
            // Recurse to left subtree for lower node values
            if (data.compareTo(node.data) < 0) {
                node.left = add(node.left, data);
            } else {
                node.right = add(node.right, data);
            }
        }
        return node;
    }

    // Remove a value from bst
    public boolean remove(T data) {
        if (contains(data)) {
            root = remove(root, data);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);

        if (cmp < 0) {
            node.left = remove(node.left, data);
        } else if (cmp > 0) {
            node.right = remove(node.right, data);
        } else {
            if (node.left == null) {
                Node rightChild = node.right;

                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;

                node.data = null;
                node = null;

                return leftChild;
            } else {
                Node tmp = digLeft(node.right); // find the leftmost node in the right subtree

                node.data = tmp.data; // swap the data
                node.right = remove(node.right, tmp.data);
            }
        }
        return node;
    }

    private Node digLeft(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public boolean contains (T data) {
        return contains(root, data);
    }

    private boolean contains(Node node, T data) {
        if (node == null) return false;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) return contains(node.left, data);
        else if (cmp > 0) return contains(node.right, data);
        else return true;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public void traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER: preOrder(root);
            case IN_ORDER: inOrder(root);
            case POST_ORDER: postOrder(root);
            case LEVEL_ORDER: levelOrder();
            default:
                System.out.println("");;
        }
    }

    private void preOrder(Node root) {
        if (root == null) return;
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    private void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    private void levelOrder() {}
}
