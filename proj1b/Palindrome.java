import java.util.Locale;

public class Palindrome {
    /**Given a string, wordToDeque should return a Deque
     * where characters appear in the same order as in the string */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    /**
     * This method should return true when the given word is palindrome
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        word = word.toLowerCase();
        Palindrome p = new Palindrome();
        Deque d = p.wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque<Character> d) {
        boolean flag = true;
        if (d.size() == 1 || d.size() == 0) {
            flag = true;
        } else {
            if(d.removeLast() != d.removeFirst()) {
                flag = false;
            } else {
                flag = isPalindromeHelper(d);
            }
        }
        return flag;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        word = word.toLowerCase();
        Palindrome p = new Palindrome();
        Deque d = p.wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> d, CharacterComparator cc) {
        boolean flag = true;
        if (d.size() == 1 || d.size() == 0) {
            flag = true;
        } else {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                flag = false;
            } else {
                flag = isPalindromeHelper(d, cc);
            }
        }
        return flag;
    }


}