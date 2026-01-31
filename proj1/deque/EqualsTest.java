package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class EqualsTest {
    @Test
    public void randomizedTest() {
        int N = 10000;
        Deque<Integer> ad = new ArrayDeque<>();
        Deque<Integer> lld = new LinkedListDeque<>();
        for (int i = 0; i < N; i++) {
            int operationNum = StdRandom.uniform(0, 3);
            int randVal = StdRandom.uniform(0, 100);
            if (operationNum == 0) {
                ad.addLast(randVal);
                lld.addLast(randVal);
            } else if (operationNum == 1) {
                assertEquals(ad, lld);
            } else {
                assertEquals(lld, ad);
            }
        }
    }
}
