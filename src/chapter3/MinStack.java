package chapter3;

import java.util.EmptyStackException;

/**
 * Problem 3.2
 * Implements a 'min stack' - all the regular stack operations including a getMin() in O(1) time
 */
public class MinStack<E extends Comparable> {
    private MyStack<E> data;
    private MyStack<E> minStack;

    public MinStack() {
        this.data = new MyStack<>();
        this.minStack = new MyStack<>();
    }

    public void push(E element) {
        data.push(element);
        if (minStack.isEmpty() || element.compareTo(minStack.peek()) <= 0) {
            minStack.push(element);
        }
    }

    public E pop() {
        if (data.isEmpty()) {
            throw new EmptyStackException();
        }
        E result = data.pop();
        if (result.compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }
        return result;
    }

    public E peek() {
        if (data.isEmpty()) {
            throw new EmptyStackException();
        }
        return data.peek();
    }

    public E getMin() {
        return minStack.peek();
    }

    @Override
    public String toString() {
        return "Data: " + data.toString() + "\nMin: " + minStack.toString();
    }
}
