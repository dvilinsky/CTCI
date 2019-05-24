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