/**
 * This class outputs all palindromes by a given offset and some statistics about palindromes
 * in the words file in the current directory.
 */
public class PalindromeFinder {
    /**
     * Outputs all palindromes by a given offset N.
     * @param N     the given offset.
     */
    public void printWordsOffByN(int N) {
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        OffByN offByN = new OffByN(N);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offByN)) {
                System.out.println(word);
            }
        }
    }

    /**
     * Finds and outputs the N for which there are the most palindromes.
     */
    public void findMostPalindromes() {
        int minLength = 4;
        int largestN = 0;
        int largestNumber = 0;

        for (int n = 0; n < 26; n++) {
            In in = new In("../library-sp19/data/words.txt");
            Palindrome palindrome = new Palindrome();
            OffByN offByN = new OffByN(n);

            int currentNumber = 0;
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, offByN)) {
                    currentNumber++;
                }
            }

            System.out.println("N: " + n + ", Word Nums: " + currentNumber);

            if (currentNumber > largestNumber) {
                largestNumber = currentNumber;
                largestN = n;
            }
        }

        System.out.println("For " + largestN + " there are most palindromes.");
        System.out.println("The number is: " + largestNumber);
    }

    /**
     * Finds and outputs the longest offByN palindrome for any N.
     */
    public void findLongest() {
        int minLength = 4;

        for (int n = 0; n < 26; n++) {
            In in = new In("../library-sp19/data/words.txt");
            Palindrome palindrome = new Palindrome();
            OffByN offByN = new OffByN(n);

            int longestLen = 0;
            String longestWord = null;

            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, offByN)) {
                    if (word.length() > longestLen) {
                        longestLen = word.length();
                        longestWord = word;
                    }
                }
            }
            System.out.println("N: " + n + ", Longest word: " + longestWord);
        }
    }

    public static void main(String[] args) {
        PalindromeFinder finder = new PalindromeFinder();
        finder.printWordsOffByN(10);
        finder.findMostPalindromes();
        finder.findLongest();
    }
}