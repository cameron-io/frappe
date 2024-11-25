package io.cameron.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}


class Traverse {
    public static void inOrder(Node node) {
        inOrderRec(node, new ArrayList<>());
    }

    public static void preOrder(Node node) {
        preOrderRec(node, new ArrayList<>());
    }

    public static void postOrder(Node node) {
        postOrderRec(node, new ArrayList<>());
    }

    public static void levelOrder(Node root, List<Integer> list) {
        if (root == null)
            return;
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            list.add(node.value);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    private static void inOrderRec(Node node, List<Integer> list) {
        if (node == null)
            return;
        inOrderRec(node.left, list);
        list.add(node.value);
        inOrderRec(node.right, list);
    }

    private static void preOrderRec(Node node, List<Integer> list) {
        if (node == null)
            return;
        list.add(node.value);
        preOrderRec(node.left, list);
        preOrderRec(node.right, list);
    }

    private static void postOrderRec(Node node, List<Integer> list) {
        if (node == null)
            return;
        postOrderRec(node.left, list);
        postOrderRec(node.right, list);
        list.add(node.value);
    }
}


public class BinaryTree {
    Node root;

    public BinaryTree() {}

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }
}
