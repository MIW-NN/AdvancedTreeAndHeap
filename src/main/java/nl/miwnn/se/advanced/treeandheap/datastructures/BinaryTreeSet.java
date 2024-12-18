package nl.miwnn.se.advanced.treeandheap.datastructures;

import java.util.NoSuchElementException;

public class BinaryTreeSet<T extends Comparable<T>> {
    private BinaryTreeNode root;
    private int size;

    public BinaryTreeSet() {
        this.root = null;
        this.size = 0;
    }

    // Add method
    public boolean add(T element) {
        if (root == null) {
            root = new BinaryTreeNode(element);
            size++;
            return true;
        }
        return addRecursive(root, element);
    }

    private boolean addRecursive(BinaryTreeNode node, T element) {
        if (element.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new BinaryTreeNode(element);
                size++;
                return true;
            }
            return addRecursive(node.left, element);
        } else if (element.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new BinaryTreeNode(element);
                size++;
                return true;
            }
            return addRecursive(node.right, element);
        }
        return false; // Element already exists
    }

    // Contains method
    public boolean contains(T element) {
        return containsRecursive(root, element);
    }

    private boolean containsRecursive(BinaryTreeNode node, T element) {
        if (node == null) return false;
        if (element.compareTo(node.value) == 0) return true;
        if (element.compareTo(node.value) < 0) return containsRecursive(node.left, element);
        return containsRecursive(node.right, element);
    }

    // Remove method
    public boolean remove(T element) {
        if (!contains(element)) return false;
        root = removeRecursive(root, element);
        size--;
        return true;
    }

    private BinaryTreeNode removeRecursive(BinaryTreeNode node, T element) {
        if (node == null) return null;

        if (element.compareTo(node.value) < 0) {
            node.left = removeRecursive(node.left, element);
        } else if (element.compareTo(node.value) > 0) {
            node.right = removeRecursive(node.right, element);
        } else {
            // Node to remove found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Node has two children
            T smallestValue = findMin(node.right);
            node.value = smallestValue;
            node.right = removeRecursive(node.right, smallestValue);
        }
        return node;
    }

    private T findMin(BinaryTreeNode node) {
        while (node.left != null) node = node.left;
        return node.value;
    }

    // Size method
    public int size() {
        return size;
    }

    // isEmpty method
    public boolean isEmpty() {
        return size == 0;
    }

    // toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return "[" + sb.toString().trim() + "]";
    }

    private void inOrderTraversal(BinaryTreeNode node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.value).append(" ");
            inOrderTraversal(node.right, sb);
        }
    }

    private class BinaryTreeNode {
        T value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}

