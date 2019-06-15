package chapter2;

import java.util.HashSet;
import java.util.Set;

public class SinglyLinkedNode {
    SinglyLinkedNode next = null;
    String data;
    int size;

    public SinglyLinkedNode(String data) {
        this.data = data;
        this.size = 0;
    }

    public void append(String data) {
        SinglyLinkedNode end = new SinglyLinkedNode(data);
        SinglyLinkedNode current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = end;
        size++;
    }


    @Override
    public String toString() {
        return this.data;
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
