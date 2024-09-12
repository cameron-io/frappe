package io.cameron.lists;

public class Queue {
    public Node head = null;
    public Node tail = null;
    public int size = 0;

    public Queue() {}

    public void enqueue(String value) {
        var new_node = new Node(value);
        if (this.size == 0) {
            this.head = new_node;
            this.tail = new_node;
        } else {
            Node prev_tail = this.tail;
            this.tail = new_node;
            prev_tail.next = new_node;
        }
        this.size++;
    }

    public void dequeue() {
        if (this.head == null) return;

        var prev_head = this.head;

        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = prev_head.next;
        }

        this.size--;
    }

    public Node get(int index) {
        if (index >= this.size) return null;
        var i = 0;
        var node = this.head;
        while(i < index) {
            node = node.next;
            i++;
        }
        return node;
    }

    public void set(int index, String value) {
        if (index >= this.size) return;
        var node = this.get(index);
        node.value = value;
    }

    public void insert(int index, String value) {
        if (index >= this.size) return;
        var prev_node = this.get(index - 1);
        var new_node = new Node(value);
        new_node.next = prev_node.next;
        prev_node.next = new_node;
        this.size++;
    }

    public void remove(int index) {
        if (index >= this.size) return;
        var prev_node = this.get(index - 1);
        var node = prev_node.next;
        prev_node.next = node.next;
        this.size--;
    }
}
