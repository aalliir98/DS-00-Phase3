package main;

public class Stack<Item> {
    private int n;
    private Node first;

    private class Node {
        private Item item;
        private Node next;
    }

    public Stack() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() throws Exception {
        if (isEmpty()) throw new Exception("Stack underflow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() throws Exception {
        if (isEmpty()) throw new Exception("Stack underflow");
        return first.item;
    }
}