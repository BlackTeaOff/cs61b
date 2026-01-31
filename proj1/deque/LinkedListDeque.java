package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T data;
        private Node prev;
        private Node next;

        Node(T i, Node p, Node n) {
            data = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node temp = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node temp = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.data);
            System.out.print(" ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node temp = sentinel.next;
        sentinel.next = temp.next;
        temp.next.prev = sentinel;
        size--;
        return temp.data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node temp = sentinel.prev;
        sentinel.prev = temp.prev;
        sentinel.prev.next = sentinel;
        size--;
        return temp.data;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        int mid = (size - 1) / 2;
        if (index <= mid) {
            Node temp = sentinel.next;
            while (index > 0) {
                temp = temp.next;
                index--;
            }
            return temp.data;
        } else {
            Node temp = sentinel.prev;
            int siz = this.size - 1;
            while (siz != index) {
                temp = temp.prev;
                siz--;
            }
            return temp.data;
        }
    }

    private T getRecursive(Node temp, int index) {
        if (index == 0) {
            return temp.data;
        }
        return getRecursive(temp.next, --index);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        LinkedListDequeIterator() {
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
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<?> other = (Deque<?>) o;
            if (size != other.size()) {
                return false;
            }
            if (o instanceof LinkedListDeque) {
                LinkedListDeque<?> otherLLD = (LinkedListDeque<?>) o;
                Node temp = sentinel.next;
                Node temp1 = (Node) otherLLD.sentinel.next;
                while (temp != sentinel) {
                    if (!temp.data.equals(temp1.data)) {
                        return false;
                    }
                    temp = temp.next;
                    temp1 = temp1.next;
                }
                return true;
            }
            Node temp = sentinel.next;
            int index = 0;
            while (temp != sentinel) {
                if (!temp.data.equals(other.get(index))) {
                    return false;
                }
                index++;
                temp = temp.next;
            }
            return true;
        }
        return false;
    }
}
