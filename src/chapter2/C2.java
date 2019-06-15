package chapter2;

import chapter2.DoublyLinkedList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        Set<SinglyLinkedNode> nodes = new HashSet<>();
        while (node != null) {
            if (nodes.contains(node)) {
                return node;
            } else {
                nodes.add(node);
                node = node.next;
            }
        }
        //Should never happen
        return null;
    }
}
