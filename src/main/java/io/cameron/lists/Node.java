package io.cameron.lists;

public class Node {
    public String value;
    public Node next = null;

    public Node(String value) {
        this.value = value;
    }

    public Node(String value, Node next) {
        this.value = value;
        this.next = next;
    }
}
