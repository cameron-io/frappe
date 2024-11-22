package io.cameron;

import org.junit.jupiter.api.Test;
import io.cameron.linked_lists.Node;
import io.cameron.linked_lists.Queue;

class TestUtils {
    public static void printNodes(Queue queue) {
        Node node = queue.head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.print("\n");
    }
}


public class QueueTest {
    @Test
    public void queueTest() {
        var queue = new Queue();
        queue.enqueue("Audi");
        queue.enqueue("BMW");
        queue.enqueue("Chevrolet");
        TestUtils.printNodes(queue);
        queue.get(2);
        queue.set(2, "Ford");
        TestUtils.printNodes(queue);
        queue.insert(1, "Ferrari");
        TestUtils.printNodes(queue);
        queue.remove(3);
        TestUtils.printNodes(queue);
    }
}
