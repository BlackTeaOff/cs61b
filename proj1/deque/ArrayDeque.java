package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
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

    @Override
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

    @Override
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T item = get(wizPos);
            wizPos++;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
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
}
