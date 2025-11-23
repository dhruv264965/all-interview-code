package SlidingWindow;

public class PrintAllPossibleSubStringOfGivenString {
    public static void main(String[] args) {
        String s = "abcd";

        // Outer loop defines the starting index of the substring
        for (int i = 0; i < s.length(); i++) {
            // Inner loop defines the ending index of the substring
            for (int j = i + 1; j <= s.length(); j++) {
                System.out.println(s.substring(i, j));
            }
        }
    }
}
