public class RecursiveTextTools {

    public static String reverse(String s) {
        if (s == null || s.length() <= 1) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }

    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        String cleaned = cleanString(s).toLowerCase();
        return checkPalindrome(cleaned, 0, cleaned.length() - 1);
    }

    private static String cleanString(String s) {
        if (s.isEmpty()) return "";
        char ch = s.charAt(0);
        String rest = cleanString(s.substring(1));
        if (Character.isLetterOrDigit(ch)) {
            return ch + rest;
        }
        return rest;
    }

    private static boolean checkPalindrome(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return checkPalindrome(s, left + 1, right - 1);
    }

    public static int countCharacter(String s, char target) {
        if (s == null || s.isEmpty()) return 0;
        int count = (s.charAt(0) == target) ? 1 : 0;
        return count + countCharacter(s.substring(1), target);
    }

    public static void main(String[] args) {
        String[] tests = {"", "a", "Level", "A man a plan a canal Panama", "Hello World"};

        for (String t : tests) {
            System.out.println("Text: \"" + t + "\"");
            System.out.println("  Reverse: \"" + reverse(t) + "\"");
            System.out.println("  Is Palindrome: " + isPalindrome(t));
            System.out.println("  Count 'a': " + countCharacter(t, 'a'));
            System.out.println("--------------------------------");
        }
    }
}
