package com.example.myapplication;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private int n;
    private Node first;

    public boolean empty() {
        return (n==0);
    }

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

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
