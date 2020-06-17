/**
 * Class OffByN.
 * This class defines how to determine equality of characters by a given offset.
 */
public class OffByN implements CharacterComparator {
    /**
     * The offset.
     */
    int offset;

    /**
     * The constructor takes N as an offset.
     * @param N     The offset
     */
    public OffByN(int N) {
        offset = N;
    }

    /**
     * Returns whether the two given characters are equal.
     * @param x     one given character
     * @param y     another given character
     * @return      whether the two given characters are equal
     */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == offset || y - x == offset;
    }
}
