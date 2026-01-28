package deque;

import net.sf.saxon.ma.arrays.ArrayFunctionSet;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private T[] data;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        data = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize() {
        T[] newData;
        if ((double)size / data.length <= 0.25 && data.length > 8) {
            newData = (T[]) new Object[data.length / 2];
        } else {
            newData = (T[]) new Object[data.length * 2];
        }
        int first = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (first >= data.length) {
                first = 0;
            }
            newData[i] = data[first];
            first++;
        }
        data = newData;
        nextFirst = data.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size >= data.length) {
            resize();
        }
        if (nextFirst < 0) {
            nextFirst = data.length - 1;
        }
        data[nextFirst] = item;
        size++;
        nextFirst--;
    }

    public void addLast(T item) {
        if (size >= data.length) {
            resize();
        }
        if (nextLast >= data.length) {
            nextLast = 0;
        }
        data[nextLast] = item;
        size++;
        nextLast++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        int first = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (first >= data.length) {
                first = 0;
            }
            System.out.print(data[first] + " ");
            first++;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((double)size / data.length <= 0.25 && data.length > 8) {
            resize();
        }
        size--;
        nextFirst++;
        if (nextFirst >= data.length) {
            nextFirst = 0;
        }
        return data[nextFirst];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((double)size / data.length <= 0.25 && data.length > 8) {
            resize();
        }
        size--;
        nextLast--;
        if (nextLast < 0) {
            nextLast = data.length - 1;
        }
        return data[nextLast];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int first = nextFirst + 1;
        if (first >= data.length) {
            first = 0;
        }
        int arrayIndex = first + index;
        if (arrayIndex >= data.length) {
            arrayIndex = arrayIndex - data.length;
        }
        return data[arrayIndex];
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = get(wizPos);
            wizPos++;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ArrayDeque) {
            ArrayDeque<?> other = (ArrayDeque<?>) o;

            if (size != other.size()) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (!get(i).equals(other.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        for (int i : ad) {
            System.out.println(i);
        }

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ArrayDeque<Integer> ad3 = new ArrayDeque<>();
        System.out.println(ad.equals(ad1));
        System.out.println(ad.equals(1));
        System.out.println(ad.equals(ad2));
        System.out.println(ad.equals(ad3));
    }
}
