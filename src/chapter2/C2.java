package chapter2;

import chapter2.DoublyLinkedList;

import java.util.Iterator;

public class C2 {
    public static void main(String[] args) {
    }

    public static void printList(DoublyLinkedList d) {
        Iterator<Integer> itr = d.iterator();
        for (Object i : d) {
            System.out.println(i);
        }
    }

    /**
     * Problem 2.8. Given head of list, returns the start of the loop. Assumes given node has a loop somewhere
     * @param node
     * @return
     */
    public static SinglyLinkedNode startOfLoop(SinglyLinkedNode node) {
        SinglyLinkedNode fast = node;
        SinglyLinkedNode slow = node;
        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return fast.next;
            }
        }
    }
}
