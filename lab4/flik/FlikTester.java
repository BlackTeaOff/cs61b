package flik;

import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTester {
    @Test
    public void test() {
        int N = 10000;
        for (int i = 0; i < N; i++) {
            if (!Flik.isSameNumber(i, i)) {
                System.out.println(i);
            }
        }
    }
}
