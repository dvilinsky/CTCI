package chapter3;

import java.util.EmptyStackException;

public class MyStack<E extends Comparable> {
    private static class Node<E extends Comparable> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> top;
    private int size;

    public void push(E data) {
        Node<E> node = new Node<>(data);
        node.next = top;
        top = node;
        size++;
    }

    public E pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        E item = top.data;
        top = top.next;
        size--;
        return item;
    }

    public E peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = top;
        if (current != null) {
            sb.append("[").append(current.data);
        }
        for (int i = 1; i < size; i++) {
            current = current.next;
            sb.append(", ").append(current.data);
        }
        sb.append("]");
        return sb.toString();
    }
}
