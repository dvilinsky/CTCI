package chapter2;

public class SinglyLinkedNode {
    SinglyLinkedNode next = null;
    int data;
    int size;

    public SinglyLinkedNode(int data) {
        this.data = data;
        this.size = 0;
    }

    public void append(int data) {
        SinglyLinkedNode end = new SinglyLinkedNode(data);
        SinglyLinkedNode current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = end;
        size++;
    }

    /**
     * Problem 2.7
     * @param other
     * @return
     */
    public SinglyLinkedNode intersectsWith(SinglyLinkedNode other) {
        SinglyLinkedNode current = this;
        if (other.size > this.size) {
            for (int i = 0; i <= other.size - this.size; i++) {
                other = other.next;
            }
        } else {
            for (int i = 0; i <= this.size - other.size; i++) {
                current = current.next;
            }
        }
        //Now the lists starting at current and other are the same length
        while (current.next != null && other.next != null) {
            if (current == next) {
                return current;
            }
            current = current.next;
            other = other.next;
        }
        return null;
    }
}
