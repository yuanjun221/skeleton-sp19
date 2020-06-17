/**
 * Interface Deque.
 * @param <T>   The generic type
 */
public interface Deque<T> {
    /**
     * Adds the item to the front of the deque.
     * @param item      the item to be added
     */
    void addFirst(T item);

    /**
     * Adds the item to the back of the deque.
     * @param item      the item to be added
     */
    void addLast(T item);

    /**
     * Returns whether the deque is empty.
     * @return      true if the deque is empty, false otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the actual number of elements in the deque.
     * @return      the number of elements in the deque
     */
    int size();

    /**
     * Prints the elements in the deque from the first index to the last.
     */
    void printDeque();

    /**
     * Removes and returns the item at the front of the deque.
     * @return      the item removed, null if no such item existed.
     */
    T removeFirst();

    /**
     * Removes and returns the item at the end of the deque.
     * @return      the item removed, null if no such item existed
     */
    T removeLast();

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * @param index     the given index
     * @return          the item at the given index, null if no such item existed
     */
    T get(int index);
}
