/**
 * Class ArrayDeque.
 * @param <T>   generic type
 */
public class ArrayDeque<T> {
    /**
     * The core array holding type T elements.
     */
    T[] items;
    /**
     * The actual number of elements hold in items.
     */
    private int size;
    /**
     * The index at the front of the deque at which the next element will be inserted.
     */
    private int nextFirst;
    /**
     * The index at the back of the deque at which the next element will be inserted.
     */
    private int nextLast;
    /**
     * The initial length of the core array.
     */
    private static final int INIT_LEN = 8;

    /**
     * Default constructor.
     */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_LEN];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Creates a deep copy of another array deque.
     * @param other     The another array deque
     */
    public ArrayDeque(ArrayDeque<T> other) {
        int len = other.items.length;
        items = (T[]) new Object[len];

        System.arraycopy(other.items, 0, items, 0, len);
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    /**
     * Adds the item to the front of the deque.
     * @param item      the item to be added
     */
    public void addFirst(T item) {
        size++;
        items[nextFirst] = item;

        if (nextFirst == 0) {
            nextFirst = items.length-1;
        } else {
            nextFirst--;
        }

        if (isFull()) {
            expand(doubleCapacity());
        }
    }

    /**
     * Adds the item to the back of the deque.
     * @param item      the item to be added
     */
    public void addLast(T item) {
        size++;
        items[nextLast] = item;
        nextLast++;

        if (isFull()) {
            expand(doubleCapacity());
        }

    }

    /**
     * Removes and returns the item at the front of the deque.
     * @return      the item removed, null if no such item existed.
     */
    public T removeFirst() {
        // Check the deque, if it's empty, do nothing and return null.
        if (isEmpty()) {
            return null;
        }

        size--;
        T removed;

        // if current first is at index 0, nextFirst must be at the end of the array
        if (nextFirst == items.length - 1) {
            removed = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            removed = items[nextFirst+1];
            items[nextFirst+1] = null;
            nextFirst++;
        }

        if (shouldReduce()) {
            shrink(halfCapacity());
        }

        return removed;
    }

    /**
     * Removes and returns the item at the end of the deque.
     * @return      the item removed, null if no such item existed
     */
    public T removeLast() {
        // Check the deque, if it's empty, do nothing and return null.
        if (isEmpty()) {
            return null;
        }

        size--;
        T removed;
        if (nextLast == 0) {
            removed = items[items.length-1];
            items[items.length-1] = null;
            nextLast = items.length - 1;
        } else {
            removed = items[nextLast-1];
            items[nextLast-1] = null;
            nextLast--;
        }

        if (shouldReduce()) {
            shrink(halfCapacity());
        }

        return removed;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * @param index     the given index
     * @return          the item at the given index, null if no such item existed
     */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        int length = items.length;
        if (index + nextFirst < length - 1) {
            return items[index + nextFirst + 1];
        }
        return items[index + nextFirst + 1 - length];
    }

    /**
     * Returns whether the deque is empty.
     * @return      true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the actual number of elements in the deque.
     * @return      the number of elements in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the elements in the deque from the first index to the last.
     */
    public void printDeque() {
        if (nextLast < nextFirst) {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i].toString() + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i].toString() + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i].toString() + " ");
            }
        }
        System.out.println();
    }

    /**
     * Expands the capacity of the core array.
     * @param capacity      the capacity of the expanded core array
     */
    private void expand(int capacity) {
        int f = nextFirst;
        int l = items.length;
        int t = l - f;

        T[] targetItems = (T[]) new Object[capacity];
        // copy from the first to the end of the array
        // srcPos: f, len: t, destPos: 0
        System.arraycopy(items,f,targetItems,0,t);
        // copy from the last to the first position of the array
        // srcPos: 0, len: f, destPos: t
        System.arraycopy(items,0,targetItems,t,f);

        items = targetItems;
        nextFirst = 0;
        nextLast = l;
    }

    /**
     * Shrinks the capacity of the core array.
     * @param capacity      the capacity of the shrunk core array
     */
    private void shrink(int capacity) {
        T[] targetItems = (T[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(items,nextFirst,targetItems,0,size+1);
            items = targetItems;
            nextFirst = 0;
            nextLast = size + 1;
        } else {
            int f = nextFirst;
            int l = items.length;
            int t = l - f;
            System.arraycopy(items,f,targetItems,0,t);
            System.arraycopy(items,0,targetItems,t,f);

            items = targetItems;
            nextFirst = 0;
            nextLast = l;
        }
    }

    /**
     * Returns the capacity the core array to be expanded.
     * Sets the capacity 2 times of the original one.
     * @return      the capacity
     */
    private int doubleCapacity() {
        int l = items.length;
        return l * 2;
    }

    /**
     * Returns the capacity the core array to be shrunk.
     * Sets the capacity half of the original one.
     * @return      the capacity
     */
    private int halfCapacity() {
        int l = items.length;
        return l / 2;
    }

    /**
     * Returns whether the core array need to be shrunk
     * The core array will be shrunk if the usage factor is less than 25%
     * @return      true if meets the usage factor limit, false otherwise
     */
    private boolean shouldReduce() {
        double reduceFactor = 0.25;
        if (items.length == INIT_LEN) {
            return false;
        }
        return (double) size / items.length < reduceFactor;
    }

    /**
     * Returns whether the core array is full
     * @return      true if it is full, false otherwise
     */
    private boolean isFull() {
        if (nextFirst == 0 && nextLast == items.length - 1) {
            return true;
        }
        return nextFirst == nextLast;
    }
}
