import org.junit.Test;
import static org.junit.Assert.*;

/**
 * class TestPalindrome
 * This class tests whether the methods of class Palindrome work correctly.
 */
public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean r = palindrome.isPalindrome("racecar");
        r = palindrome.isPalindrome("noon") && r;
        assertTrue(r);
    }
}