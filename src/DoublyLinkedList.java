import java.util.HashSet;
import java.util.Set;

/**
 * This class implements a circular doubly linked list that ends up having an interesting API due to the questions asked
 * in CTCI Chapter 2
 * @param <E>
 */

public class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;


    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Appends a node to the end of the list.
     * Running time: O(1)
     * @param data
     */
    public void add(E data) {
        Node<E> end = new Node<>(data);
        if (this.head == null && this.tail == null) {
            this.head = end;
            end.next = end;
            end.previous = end;
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
}

class Node<E> {
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