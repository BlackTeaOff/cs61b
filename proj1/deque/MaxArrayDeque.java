package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }

    public T max() {
        return max(c);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxDex = 0;
        for (int i = 1; i < size(); i++) {
            int cmp = c.compare(get(i), get(maxDex));
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }

    public static void main(String[] args){
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(5);
        mad.addFirst(8);
        mad.addFirst(2);
        mad.addFirst(3);

        System.out.println(mad.max());
    }
}
