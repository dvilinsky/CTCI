package chapter2;

import chapter2.DoublyLinkedList;

import java.util.Iterator;

public class C2 {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> d = new DoublyLinkedList<>();
        d.append(7);
        d.append(1);
        d.append(6);
//        chapter2.DoublyLinkedList<Integer> l = new chapter2.DoublyLinkedList<>();
        d.append(5);
        d.append(9);
        d.append(2);
//        System.out.println(l.sumWith(d, true));
    }

    public static void printList(DoublyLinkedList d) {
        Iterator<Integer> itr = d.iterator();
        for (Object i : d) {
            System.out.println(i);
        }
    }
}
