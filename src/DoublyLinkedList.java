import java.util.HashSet;
import java.util.Set;

/**
 * This class implements a very basic circular doubly linked list
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
        if (this.tail != null) {
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else {
                this.tail.previous.next = this.head;
                this.head.previous = this.tail.previous;
                this.tail = this.tail.previous;
            }
            this.size--;
        }
    }

    /** Problem 2.1
     * Removes duplicate elements from the list
     * Running time: O(n) or O(n^2), depending on if client wants to use extra space or extra time
     * Space: O(n) or O(1), depending on if client wants to use extra space or extra time
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