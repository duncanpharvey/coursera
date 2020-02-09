import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        Node newfirst = new Node();
        if (first != null) {
            first.prev = newfirst;
        }
        newfirst.item = item;
        newfirst.next = first;
        newfirst.prev = null;
        first = newfirst;
        size += 1;
        if (size == 1) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        Node newlast = new Node();
        if (last != null) {
            last.next = newlast;
        }
        newlast.item = item;
        newlast.next = null;
        newlast.prev = last;
        last = newlast;
        size += 1;
        if (size == 1) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("deque must not be empty");
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        size -= 1;
        if (size <= 1) {
            last = first;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("deque must not be empty");
        }
        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        size -= 1;
        if (size <= 1) {
            first = last;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("no more items to return in deque");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove method not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.print(deque.size());
        StdOut.print(", size = 0\n");

        StdOut.print(String.valueOf(deque.isEmpty()));
        StdOut.print(", isEmpty = true\n");

        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        for (int d : deque)
            StdOut.print(d);
        StdOut.print(", deque = 123456\n");

        StdOut.print(deque.size());
        StdOut.print(", size = 6\n");

        StdOut.print(String.valueOf(deque.isEmpty()));
        StdOut.print(", isEmpty = false\n");

        int removed1 = deque.removeFirst();
        int removed2 = deque.removeLast();

        StdOut.print(removed1);
        StdOut.print(", removed = 1\n");
        StdOut.print(removed2);
        StdOut.print(", removed = 6\n");

        StdOut.print(deque.size());
        StdOut.print(", size = 4\n");

        for (int d : deque)
            StdOut.print(d);
        StdOut.println(", deque = 2345\n");
    }

}
