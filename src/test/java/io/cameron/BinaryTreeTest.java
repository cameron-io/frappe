package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

import io.cameron.trees.BinaryTree;
import io.cameron.trees.Node;
import io.cameron.trees.Traverse;

public class BinaryTreeTest {
    private BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }

    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
        BinaryTree bt = createBinaryTree();

        assertTrue(bt.containsNode(6));
        assertTrue(bt.containsNode(4));

        assertFalse(bt.containsNode(1));
    }

    @Test
    public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
        BinaryTree bt = createBinaryTree();

        assertTrue(bt.containsNode(9));
        bt.delete(9);
        assertFalse(bt.containsNode(9));
    }

    @Test
    public void givenABinaryTree_WhenTraversingElements_ThenTreeReturnsExpectedOrder() {
        BinaryTree bt = createBinaryTree();
        Node root = bt.getRoot();
        assertNotEquals(null, root);
        assertEquals(List.of(3, 4, 5, 6, 7, 8, 9), Traverse.inOrder(root));
        assertEquals(List.of(6, 4, 3, 5, 8, 7, 9), Traverse.preOrder(root));
        assertEquals(List.of(3, 5, 4, 7, 9, 8, 6), Traverse.postOrder(root));
        assertEquals(List.of(6, 4, 8, 3, 5, 7, 9), Traverse.levelOrder(root));
    }
}
