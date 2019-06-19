package chapter3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem 3.1
 * Implements three stacks using one array. For proof of concept, it only accepts integers, but could be easily
 * extended to generics (although java doesn't really care for generic arrays)
 */
public class ThreeStack {
    private static final int DEFAULT_CAPACITY = 21;
    private int[] backingArray;
    private Map<Integer, Integer> tops;
    private Map<Integer, Integer> limits;

    public ThreeStack(int initialCapacity) {
        backingArray = new int[initialCapacity];
        tops = new HashMap<>();
        limits = new HashMap<>();
        tops.put(0, 0);
        tops.put(1, initialCapacity/3 + 1);
        tops.put(2, initialCapacity * 2 / 3 + 1);
        updateLimits(backingArray);
    }

    public ThreeStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Pushes given data onto given stack
     * @param data the data to be added to one of the stack
     * @param stack the stack to which to add the data (must be between 0-2, inclusive)
     */
    public void push(int data, int stack) {
        if (stack > 2 || stack < 0) {
            throw new IndexOutOfBoundsException("Error; Invalid Stack");
        }
        int top = tops.get(stack);
        if (isFull(stack, top)) {
            expand();
        }
        backingArray[top++] = data;
        tops.put(stack, top);
    }

    /**
     * Returns without removing the top element of a given stack
     * @param stack the stack whose top element you want to view
     * @return the top of element of the given stack
     */
    public int peek(int stack) {
        return backingArray[tops.get(stack) - 1];
    }

    /**
     * Removes and returns the top element of a given stack
     * @param stack the stack whose top element you want to return
     * @return the top element of the given stack
     */
    public int pop(int stack) {
        int newTop = tops.get(stack) - 1;
        int data = backingArray[newTop];
        tops.put(stack, newTop);
        return data;
    }

    private boolean isFull(int stack, int top) {
       return tops.get(stack) == limits.get(stack);
    }

    /**
     * Expands the capacity of the backing array. This copies elements into the backing array into a new array
     * of size 2 * backingArray.length. We expand every time any one of the stacks is full.
     */
    private void expand() {
        int[] newArray = new int[backingArray.length * 2];
        int index = 0;
        int currentStack = 0;
        for (int i = 0; i < backingArray.length; i++) {
            if (currentStack != 2 && i == tops.get(currentStack)) {
                tops.put(currentStack, index);
                index = ((currentStack + 1) * newArray.length/3 + 1);
                i = ((currentStack + 1) * backingArray.length/3 + 1);
                currentStack++;
            }
            newArray[index++] = backingArray[i];
        }
        tops.put(currentStack, index);
        backingArray = newArray;
        updateLimits(backingArray);
    }

    private void updateLimits(int[] backingArray) {
        limits.put(0, backingArray.length/3 + 1);
        limits.put(1, backingArray.length * 2 / 3 + 1);
        limits.put(2, backingArray.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(backingArray);
    }
}
