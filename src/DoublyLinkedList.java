import java.util.*;

/**
 * This class implements a circular doubly linked list that ends up having an interesting API due to the questions asked
 * in CTCI Chapter 2
 * @param <E>
 */

public class DoublyLinkedList<E extends Comparable> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;


    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds a node containing data to front of list
     * Running time: O(1)
     * @param data Data to be contained in new node
     */
    public void shift(E data) {
        Node<E> front = new Node<>(data);
        if (isEmpty()) {
            addEmpty(front);
            this.tail = front;
        } else {
            head.previous = front;
            tail.next = front;
            front.next = head;
            front.previous = tail;
            head = front;
        }
        size++;
    }

    /**
     * Adds a node to list when list is empty
     * @param node Data to be contained in new node
     */
    private void addEmpty(Node<E> node) {
        head = node;
        node.next = node;
        node.previous = node;
    }

    /**
     * Appends a node to the end of the list.
     * Running time: O(1)
     * @param data Data to be contained in new node
     */
    public void append(E data) {
        Node<E> end = new Node<>(data);
        if (isEmpty()) {
            addEmpty(end);
        } else {
            this.tail.next = end;
            end.previous = this.tail;
            end.next = head;
            head.previous = end;
        }
        this.tail = end;
        this.size++;
    }

    /**
     * Removes the value from the end of the list
     * Running time; O(1)
     */
    public void delete() {
       removeNode(tail);
    }


    /** Problem 2.1
     * Removes duplicate elements from the list
     * Running time: O(n) or O(n^2), depending on if client wants to use extra space or extra time, respectively
     * Space: O(n) or O(1), depending on if client wants to use extra space or extra time, respectively
     */
    public void removeDuplicates(boolean useExtraSpace) {
        if (useExtraSpace) {
            removeUseExtraSpace();
        } else {
            removeUseExtraTime();
        }
    }

    private void removeUseExtraTime() {
        Node<E> current = head;
        Node<E> seeker =  current.next;
        int numRemoved = 0;
        for (int i = 0; i < this.size; i++) {
            while(seeker != head) {
                if (seeker.data == current.data) {
                    //remove node seeker is pointing to
                    seeker.previous.next = seeker.next;
                    seeker.next.previous = seeker.previous;
                    numRemoved++;
                    if (seeker == tail) {
                        System.out.println("hello world");
                        tail = seeker.previous;
                    }
                }
                seeker = seeker.next;
            }
            current = current.next;
            seeker = current.next;
        }
        this.size -= numRemoved;
    }

    private void removeUseExtraSpace() {
        Node<E> current = head;
        Set<E> duplicates = new HashSet<>();
        int numRemoved = 0;
        for (int i = 0; i < this.size; i++) {
            if (duplicates.contains(current.data)) {
                //remove current from list
                current.previous.next = current.next;
                current.next.previous = current.previous;
                if (current == tail) {
                    tail = current.previous;
                }
                numRemoved++;
            } else {
                duplicates.add(current.data);
            }
            current = current.next;
        }
        this.size -= numRemoved;
    }

    /**
     * Problem 2.2
     * Since we have a doubly-linked list with a pointer to the head, the most-efficient implementation would be
     * walk k steps backwards from the head. However, here I am acting as though we only bave a singly linked list
     * for the sake of answering the question as it was asked. I'm also pretending we don't have a size field
     * Running time: O(n)
     * Space; O(1)
     * @param k
     */
    public void removeKthToLast(int k) {
        if (isEmpty()) {
            return;
        }
        int index = getIndex(k);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        removeNode(current);
    }

    private int getIndex(int k) {
        int size = 1; //start at 1 b/c we will stop looping right before tail
        Node<E> current = head;
        while (current != tail) {
            size++;
            current = current.next;
        }
        return size - k;
    }

    /**
     * Removes a given node from this last. Also answers problem 2.3
     * @param node The node to remove
     */
    private void removeNode(Node<E> node) {
        if (size <= 1) {
            this.head = null;
            this.tail = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            if (node == head) {
                head = node.next;
            } else if (node == tail) {
                tail = node.previous;
            }
        }
        this.size--;
    }

    /**
     * Problem 2.4
     * Partitions list around x, such that all values less than x come before all values greater than or equal to x
     * Running time: O(n)
     * Space: O(n). Tried to do it in place, really difficult w/ doubly linked circular list
     * @param x The value around which to partition the list
     */
    @SuppressWarnings("unchecked")
    public void partition(int x) {
        Node<E> current = head;
        int length = size;
        size = 0;
        head = null;
        tail = null;
        for (int i = 0; i < length; i++) {
            E x1 = (E) Integer.valueOf((Integer) current.data);
            if (current.data.compareTo(x) < 0) {
                this.shift(x1);
            } else {
                this.append(x1);
            }
            current = current.next;
        }
    }

    /**
     * Problem 2.5
     * Assuming that this list and l are both lists where each node represents a digit in an integer, returns the sum
     * of the values the lists represent. Again, for sake of answering the CTCI question as asked, the algorithm operates
     * as though this were a singly-linked list. The question actually says to return a new list, but once you
     * have the sum, returning a new list is trivial
     * Both lists must represent numbers in the same order as the other list
     * @param l The other list representing a number
     * @param reversed Whether digits are stored in reverse order or not
     * @return sum of values represented by each list
     */
    public int sumWith(DoublyLinkedList<Integer> l, boolean reversed) {
        if (reversed) {
           return doSumReverse(l);
        } else {
            return doSumNotReverse(l);
        }
    }

    private int doSumReverse(DoublyLinkedList<Integer> l) {
        int thisVal = 0;
        int lVal = 0;
        int currentDecimalMultiplier = 1;
        for (E i : this) {
            thisVal += ((Integer) i * currentDecimalMultiplier);
            currentDecimalMultiplier *= 10;
        }
        currentDecimalMultiplier = 1;
        for (int i : l) {
            lVal += (i * currentDecimalMultiplier);
            currentDecimalMultiplier *= 10;
        }
        return thisVal + lVal;
    }

    private int doSumNotReverse(DoublyLinkedList<Integer> l) {
        Stack<Integer> thisStack = new Stack<>();
        Stack<Integer> lStack = new Stack<>();
        for (E i : this) {
            thisStack.push((Integer) i);
        }
        for (int i : l) {
            lStack.push(i);
        }
        //At this point, the algorithm is the same as when the lists are reversed.
        //TODO: Figure out how to make doSumReverse generic enough to be able to call from this point
        int thisVal = 0;
        int lVal = 0;
        int currentDecimalMultiplier = 1;
        for (int i: thisStack) {
            thisVal += ((Integer) i * currentDecimalMultiplier);
            currentDecimalMultiplier *= 10;
        }
        for (int i : lStack) {
            lVal += (i * currentDecimalMultiplier);
            currentDecimalMultiplier *= 10;
        }
        return thisVal + lVal;
    }

    /**
     * Returns true when the list has size of 0, false otherwise
     * @return If the list is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Gives a string representation of this list of the format [val1, val2, val3, ... ,valN]
     * Running time: O(n)
     * @return String representation of the list
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<E> current = this.head;
        for (int i = 0; i < this.size; i++) {
            s.append(current.data).append(", ");
            current = current.next;
        }
        s.append("]");
        return s.toString();
    }

    public Iterator<E> iterator() {
        return new DLLIterator(this);
    }

    /**
     * Implements an Iterator for the DoublyLinkedList class
     */
    private class DLLIterator implements Iterator<E> {
        Node<E> current;
        int size;
        int currentSize;

        DLLIterator(DoublyLinkedList<E> l) {
            current = l.head;
            size = l.size;
            currentSize = 0;
        }

        @Override
        public boolean hasNext() {
            return currentSize != size;
        }

        @Override
        public E next() {
            if (hasNext()) {
                currentSize++;
                E data = current.data;
                current = current.next;
                return data;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}

class Node<E extends Comparable> {
    public E data;
    public Node<E> next;
    public Node<E> previous;

    public Node(E data) {
        this.data = data;
    }

    public Node(E data, Node<E> next, Node<E> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}