package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        StringBuilder fs = new StringBuilder();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();

        int N = 10000;
        for (int i = 0; i < N; i++) {
            int operationNum = StdRandom.uniform(0, 4);
            int randVal = StdRandom.uniform(0, 100);
            if (operationNum == 0) {
                fs.append("addFirst(").append(randVal).append(")\n");
                sad.addFirst(randVal);
                ad.addFirst(randVal);
            } else if (operationNum == 1) {
                fs.append("addLast(").append(randVal).append(")\n");
                sad.addLast(randVal);
                ad.addLast(randVal);
            } else if (operationNum == 2) {
                if (!ad.isEmpty()) {
                    fs.append("removeFirst()\n");
                    assertEquals(fs.toString(), ad.removeFirst(), sad.removeFirst());
                }
            } else {
                if (!ad.isEmpty()) {
                    fs.append("removeLast()\n");
                    assertEquals(fs.toString(), ad.removeLast(), sad.removeLast());
                }
            }
        }

    }
}
