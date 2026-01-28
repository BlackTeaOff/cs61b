package deque;

import jh61b.junit.In;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private class Node {
        public T data;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
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

    public void addFirst(T item) {
        Node temp = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size++;
    }

    public void addLast(T item) {
        Node temp = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.data);
            System.out.print(" ");
            temp = temp.next;
        }
        System.out.println();
    }

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
            int size = this.size - 1;
            while (size != index) {
                temp = temp.prev;
                size--;
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

        public LinkedListDequeIterator() {
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
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque) {
            LinkedListDeque<?> other = (LinkedListDeque<?>) o;
            if (size != other.size) {
                return false;
            }
            Node temp = sentinel.next;
            Node temp1 = (Node) other.sentinel.next;
            while (temp != sentinel) {
                if (!temp.data.equals(temp1.data)) {
                    return false;
                }
                temp = temp.next;
                temp1 = temp1.next;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        lld.addLast(4);
        lld.addLast(5);

        for (int i : lld) {
            System.out.println(i);
        }
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        System.out.println(lld.equals(lld1));
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        System.out.println(lld.equals(lld2));
    }
}
