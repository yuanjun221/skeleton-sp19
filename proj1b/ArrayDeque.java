/**
 * Class ArrayDeque.
 * @param <T>   generic type
 */
public class ArrayDeque<T> implements Deque<T> {
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
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            expand();
        }

        size++;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }

    /**
     * Adds the item to the back of the deque.
     * @param item      the item to be added
     */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            expand();
        }

        size++;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
    }

    /**
     * Returns the actual number of elements in the deque.
     * @return      the number of elements in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the elements in the deque from the first index to the last.
     */
    @Override
    public void printDeque() {
        int actualIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[actualIndex] + " ");
            actualIndex = plusOne(actualIndex);
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * @return      the item removed, null if no such item existed.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        size--;
        nextFirst = plusOne(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;

        if (isSparse()) {
            shrink();
        }
        return toRemove;
    }

    /**
     * Removes and returns the item at the end of the deque.
     * @return      the item removed, null if no such item existed
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        size--;
        nextLast = minusOne(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;

        if (isSparse()) {
            shrink();
        }
        return toRemove;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * @param index     the given index
     * @return          the item at the given index, null if no such item existed
     */
    @Override
    public T get(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }

        int actualIndex = (plusOne(nextFirst) + index) % items.length;
        return items[actualIndex];
    }

    /**
     * Resizes the core array by a given capacity.
     * @param capacity      the given capacity.
     */
    private void resize(int capacity) {
        T[] targetItems = (T[]) new Object[capacity];

        int actualIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            targetItems[i] = items[actualIndex];
            actualIndex = plusOne(actualIndex);
        }
        items = targetItems;

        nextFirst = minusOne(targetItems.length);
        nextLast = size;
    }

    /**
     * Expands the capacity of the core array.
     */
    private void expand() {
        resize(doubleCapacity());
    }

    /**
     * Shrinks the capacity of the core array.
     */
    private void shrink() {
       resize(halfCapacity());
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
    private boolean isSparse() {
        double FACTOR = 0.25;
        if (items.length == INIT_LEN) {
            return false;
        }
        return (double) size / items.length < FACTOR;
    }

    /**
     * Returns whether the core array is full
     * @return      true if it is full, false otherwise
     */
    private boolean isFull() {
        return size == items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }
}
