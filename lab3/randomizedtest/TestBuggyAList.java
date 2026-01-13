package randomizedtest;

import com.sun.tools.corba.se.idl.InterfaceGen;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> list = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();

        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        buggyList.addLast(4);
        buggyList.addLast(5);
        buggyList.addLast(6);

        assertEquals(list.removeLast(), buggyList.removeLast());
        assertEquals(list.removeLast(), buggyList.removeLast());
        assertEquals(list.removeLast(), buggyList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyL =  new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyL.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int buggySize = buggyL.size();
                //System.out.println("size: " + size);
                //System.out.println("buggySize: " + buggySize);
                assertEquals(size, buggySize);
            } else {
                // getLast 和 removeLast
                if (L.size() > 0) {
                    if (operationNumber == 2) {
                        // getLast
                        int last = L.getLast();
                        int buggyLast = buggyL.getLast();
                        //System.out.println("getLast("+ last +")");
                        //System.out.println("buggyGetLast("+ buggyLast +")");
                        assertEquals(last, buggyLast);
                    } else {
                        // removeLast
                        int removeLast = L.removeLast();
                        int buggyRemoveLast = buggyL.removeLast();
                        //System.out.println("removeLast("+ removeLast +")");
                        //System.out.println("buggyRemoveLast("+ buggyRemoveLast +")");
                    }
                }
            }
        }
    }
}
