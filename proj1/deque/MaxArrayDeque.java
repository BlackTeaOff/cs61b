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

    public T max(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        }
        int maxDex = 0;
        for (int i = 1; i < size(); i++) {
            int cmp = comparator.compare(get(i), get(maxDex));
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }
}
