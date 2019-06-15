package chapter2;

import chapter2.DoublyLinkedList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class C2 {
    public static void main(String[] args) {
        SinglyLinkedNode list = makeCircularList(3, 5);
        printCircularList(list);
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
        int size = 0;
        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        return null;
//        Set<SinglyLinkedNode> nodes = new HashSet<>();
//        while (node != null) {
//            if (nodes.contains(node)) {
//                return node;
//            } else {
//                nodes.add(node);
//                node = node.next;
//            }
//        }
//        //Should never happen if guaranteed input has loop
//        return null;
    }

    public static SinglyLinkedNode makeCircularList(int loopSize, int listSize) {
        char data = 'A';
        SinglyLinkedNode head = new SinglyLinkedNode(String.valueOf(data));
        SinglyLinkedNode start = head;
        for (int i = 0; i < listSize-1; i++) {
            SinglyLinkedNode node = new SinglyLinkedNode(String.valueOf(++data));
            head.next = node;
            head = node;
        }
        head.next = null;
        SinglyLinkedNode current = start;
        for (int i = 0; i < listSize - loopSize; i++) {
            current = current.next;
        }
        head.next = current;
        return start;
    }

    public static void printCircularList(SinglyLinkedNode head) {
        Set<SinglyLinkedNode> nodes = new HashSet<>();
        while (!nodes.contains(head)) {
            System.out.print(head + " ");
            nodes.add(head);
            head = head.next;
        }
        System.out.print(head);
    }
}
