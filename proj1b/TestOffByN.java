import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Class TestOffByN.
 * This class tests whether the class TestOffByN works correctly.
 */
public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testOffByN() {
        boolean r = palindrome.isPalindrome("abdgf", offByN);
        assertTrue(r);
    }
}
