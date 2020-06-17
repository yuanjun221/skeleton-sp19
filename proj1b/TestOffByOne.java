import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class TestOffByOne.
 * This class tests whether the class TestOffByOne works correctly.
 */
public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testOffByOne() {
        boolean r = palindrome.isPalindrome("flake", offByOne);
        assertTrue(r);
    }
}