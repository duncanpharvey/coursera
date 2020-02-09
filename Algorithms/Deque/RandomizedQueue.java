import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] q = (Item[]) new Object[4];

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = q[i];
        q = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        if (q.length == size) {
            resize(2 * q.length);
        }
        q[size] = item;
        size += 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("queue must not be empty");
        }
        int index = StdRandom.uniform(size);
        Item item = q[index];
        q[index] = q[size - 1];
        q[size - 1] = null;
        size -= 1;
        if (size > 0 && q.length / 4 == size) {
            resize(q.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("queue must not be empty");
        }
        return q[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int index = size;
        private final Item[] randq = randomizedqueue();

        private Item[] randomizedqueue() {
            Item[] copy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copy[i] = q[i];
            }
            StdRandom.shuffle(copy);
            return copy;
        }

        public boolean hasNext() {
            return index > 0;
        }

        public Item next() {
            if (index == 0) {
                throw new NoSuchElementException("no more items to return in deque");
            }
            return randq[--index];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove method not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        StdOut.print(queue.size());
        StdOut.print(", size = 0\n");

        StdOut.print(String.valueOf(queue.isEmpty()));
        StdOut.print(", isEmpty = true\n");

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        for (int q : queue)
            StdOut.print(q);
        StdOut.print(", queue = 1234 in random order\n");

        StdOut.print(queue.size());
        StdOut.print(", size = 4\n");

        StdOut.print(String.valueOf(queue.isEmpty()));
        StdOut.print(", isEmpty = false\n");

        int removed1 = queue.dequeue();
        int removed2 = queue.dequeue();
        int removed3 = queue.dequeue();

        StdOut.print(removed1);
        StdOut.print(", removed = random\n");
        StdOut.print(removed2);
        StdOut.print(", removed = random\n");
        StdOut.print(removed3);
        StdOut.print(", removed = random\n");

        StdOut.print(queue.size());
        StdOut.print(", size = 1\n");

        for (int q : queue)
            StdOut.print(q);
        StdOut.println(", queue = 1, 2, 3, or 4\n");
    }
}
