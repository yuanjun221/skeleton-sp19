/**
 * Class LinkedListDeque.
 * @param <T>   generic type
 */
public class LinkedListDeque<T> {
    /**
     * Inner class Node.
     * @param <T>   generic type
     */
    private static class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node(T i, Node<T> p, Node<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * The sentinel acts as a helper node, dealing with the situation
     * such as to get item at front or back of the deque easily
     */
    private final Node<T> sentinel;

    /**
     * The number of the items in the deque.
     */
    private int size;

    /**
     * Default Constructor.
     */
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Creates a deep copy of other.
     * @param other another deque to be copied
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node<>(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /**
     * Add the item of type T to the front of the deque.
     * @param item  The item to be added
     */
    public void addFirst(T item) {
        size++;
        Node<T> newNode = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    /**
     * Adds the item of type T to the back of the deque.
     * @param item  The item to be added
     */
    public void addLast(T item) {
        size++;
        Node<T> newNode = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    /**
     * Return whether the deque is empty.
     * @return  true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items in the deque.
     * @return  the number of the items in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last.
     */
    public void printDeque() {
        Node<T> n = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(n.item + " ");
            n = n.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * @return  the item removed, null if no such item existed
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        size--;
        Node<T> node = sentinel.next;
        sentinel.next = node.next;
        node.next.prev = sentinel;
        node.next = null;
        node.prev = null;

        return node.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * @return  the item removed, null if no such item existed
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        size--;
        Node<T> node = sentinel.prev;
        sentinel.prev = node.prev;
        node.prev.next = sentinel;
        node.prev = null;
        node.next = null;

        return node.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * @param index the index of a item
     * @return  the item at the given index, null if no such item existed
     */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        /*
         * For the sake of the doubly linked implementation, comparing the index with size / 2
         * to decide whether to iterate through the deque in a forward or backward sequence.
         * This algorithm will reduce half of the time consuming at most when iterating through the deque.
         */
        if (index <= size / 2) {
            Node<T> node = sentinel.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.item;
        }
        Node<T> node = sentinel.prev;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * @param index index the index of a item
     * @return  the item at the given index, null if no such item existed
     */
    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index <= size / 2) {
            return getRecursiveForward(sentinel.next, index);
        }
        return getRecursiveBackward(sentinel.prev, index);
    }

    /**
     * A helper method to get the item at a given index no bigger than size / 2.
     * The method goes through the deque from the sentinel node forward.
     * @param node  The current node
     * @param index The given index
     * @return  The item of the node of the given index
     */
    private T getRecursiveForward(Node<T> node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveForward(node.next,index - 1);
    }

    /**
     * A helper method to get the item at a given index bigger than size / 2.
     * The method goes through the deque from the sentinel node backward.
     * @param node  The current node
     * @param index The given index
     * @return  The item of the node of the given index
     */
    private T getRecursiveBackward(Node<T> node, int index) {
        if (index == size - 1) {
            return node.item;
        }
        return getRecursiveBackward(node.prev,index + 1);
    }
}
