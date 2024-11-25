package io.cameron.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traverse {
    public static List<Integer> inOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        inOrderRec(node, list);
        return list;
    }

    public static List<Integer> preOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        preOrderRec(node, list);
        return list;
    }

    public static List<Integer> postOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        postOrderRec(node, list);
        return list;
    }

    public static List<Integer> levelOrder(Node node) {
        List<Integer> list = new ArrayList<>();
        levelOrder(node, list);
        return list;
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

    private static void levelOrder(Node root, List<Integer> list) {
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
}
