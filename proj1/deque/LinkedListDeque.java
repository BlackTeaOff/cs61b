package deque;

public class LinkedListDeque<T> {
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
//        Node temp = sentinel.next;
//        while (index > 0) {
//            temp = temp.next;
//            index--;
//        }
//        return temp.data;

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
}
