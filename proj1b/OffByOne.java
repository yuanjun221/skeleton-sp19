/**
 * Class OffByOne.
 * This class defines how to determine equality of characters by fixed offset 1.
 */
public class OffByOne implements CharacterComparator{

    /**
     * Returns whether the two given characters are equal.
     * @param x     one given character
     * @param y     another given character
     * @return      whether the two given characters are equal
     */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == 1 || y - x == 1;
    }
}
