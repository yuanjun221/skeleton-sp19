public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out get checks. */
    public static boolean checkGet(int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the deque, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());
        ad1.addFirst("front");

        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast("middle");
        passed = checkSize(2, ad1.size()) && passed;

        ad1.addLast("back");
        passed = checkSize(3,ad1.size()) && passed;

        ad1.addFirst("tail");

        System.out.println("Printing out the deque: ");
        ad1.printDeque();

        printTestStatus(passed);
    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    public static void addRemoveTest() {
        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(10);
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeFirst();
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        ad1.addLast(20);
        passed = checkEmpty(false,ad1.isEmpty()) && passed;

        ad1.removeLast();
        passed = checkEmpty(true,ad1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    /**
     * Adds some items, then get an item at a given index, and ensures get returns the correct item.
     */
    public static void getTest() {
        System.out.println("Running get test");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(20);       // 2
        ad1.addFirst(15);       // 1
        ad1.addFirst(10);       // 0
        ad1.addLast(25);        // 3
        ad1.addLast(30);        // 4

        boolean passed = checkGet(10,ad1.get(0));
        passed = checkGet(15,ad1.get(1)) && passed;
        passed = checkGet(20,ad1.get(2)) && passed;
        passed = checkGet(25,ad1.get(3)) && passed;
        passed = checkGet(30,ad1.get(4)) && passed;

        printTestStatus(passed);
    }

    /**
     * Adds the bunch of items, then deletes them. To see whether the expand and shrink methods work correctly.
     */
    public static void resizeTest() {
        System.out.println("Running resize test");

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                ad1.addFirst(i);
            } else {
                ad1.addLast(i);
            }
        }

        ad1.printDeque();

        for (int i = 1; i <= 99; i++) {
            if (i % 2 == 0) {
                ad1.removeLast();
            } else {
                ad1.removeFirst();
            }
        }

        ad1.printDeque();

    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        getTest();
        resizeTest();
    }
}
