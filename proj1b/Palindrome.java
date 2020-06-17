/**
 * Class Palindrome.
 * This class defines methods for checking whether a string is palindrome.
 */
public class Palindrome {
    /**
     * Returns a Deque of type Character by a given string.
     * Can be implemented by LinkedListDeque or Array Deque.
     * @param word      a given string
     * @return          a deque
     */
    public Deque<Character> wordToDeque(String word) {
        // Using LinkedListDeque
        /*
        LinkedListDeque<Character> lld = new LinkedListDeque<>();
        char[] chArr = word.toCharArray();
        for (char ch : chArr) {
            lld.addLast(ch);
        }
        return lld;
        */

        // Using ArrayDeque
        ArrayDeque<Character> ad = new ArrayDeque<>();
        char[] chArr = word.toCharArray();
        for (char ch : chArr) {
            ad.addLast(ch);
        }
        return ad;
    }

    /**
     * Returns whether a given string is a palindrome.
     * @param word      a given string
     * @return          true if the string is palindrome, false otherwise
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    /**
     * A helper method returns whether characters in a given deque are palindrome.
     * @param deque     a given deque
     * @return          true if the characters in the deque are palindrome, false otherwise
     */
    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        char first = deque.removeFirst();
        char last = deque.removeLast();
        if (first == last) {
            return isPalindrome(deque);
        }
        return false;
    }

    /**
     * Returns whether a given string is palindrome compared by a given character comparator.
     * @param word      the given string
     * @param cc        the given character comparator
     * @return          true if the string is palindrome, false otherwise
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    /**
     * A helper method returns whether characters in a given deque are palindrome.
     * @param deque     the given deque
     * @param cc        the given character comparator
     * @return          true if the characters in the deque are palindrome, false otherwise
     */
    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        char first = deque.removeFirst();
        char last = deque.removeLast();
        if (cc.equalChars(first, last)) {
            return isPalindrome(deque, cc);
        }
        return false;
    }
}
