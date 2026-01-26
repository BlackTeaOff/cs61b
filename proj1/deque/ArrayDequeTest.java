package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;
// import java.util.ArrayDeque;
import java.util.LinkedList;

public class ArrayDequeTest {

    @Test
    public void test() {
        deque.ArrayDeque<Integer> ad1 = new deque.ArrayDeque<>();
        ad1.isEmpty();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
//        System.out.println(ad1.size());
        ad1.printDeque();
//        System.out.println(ad1.get(0));
//        System.out.println(ad1.get(1));
//        System.out.println(ad1.get(2));
//        System.out.println(ad1.get(3));
//        System.out.println(ad1.get(4));
//        System.out.println(ad1.get(5));
//        System.out.println(ad1.get(6));
//        System.out.println(ad1.get(7));
//        System.out.println(ad1.get(8));
        System.out.println(ad1.removeFirst());
        System.out.println(ad1.removeFirst());
        System.out.println(ad1.removeFirst());
        System.out.println(ad1.removeLast());
        System.out.println(ad1.removeLast());
        System.out.println(ad1.removeLast());
        System.out.println(ad1.removeLast());
        ad1.printDeque();
    }

    @Test
    public void randomizedTest() {
        deque.ArrayDeque<Integer> myList = new deque.ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();

        int N = 10000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 7);
            int randVal = StdRandom.uniform(0, 100);
            if (operationNumber == 0) {
                myList.addFirst(randVal);
                list.addFirst(randVal);
            } else if (operationNumber == 1) {
                myList.addLast(randVal);
                list.addLast(randVal);
            } else if (operationNumber == 2) {
                assertEquals(list.isEmpty(), myList.isEmpty());
            } else if (operationNumber == 3) {
                assertEquals(list.size(), myList.size());
            } else if (operationNumber == 4) {
                if (!list.isEmpty()) {
                    assertEquals(list.removeFirst(), myList.removeFirst());
                }
            } else if (operationNumber == 5) {
                if (!list.isEmpty()) {
                    assertEquals(list.removeLast(), myList.removeLast());
                }
            } else {
                if (!list.isEmpty()) {
                    int randIndex = StdRandom.uniform(0, list.size());
                    assertEquals(list.get(randIndex), myList.get(randIndex));
                }
            }
        }
    }
}
