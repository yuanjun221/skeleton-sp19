public class Palindrome {
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

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

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

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

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
