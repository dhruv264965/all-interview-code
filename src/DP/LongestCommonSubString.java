package DP;

public class LongestCommonSubString {
    static String longetsCommonSubStringBruteForce(String s1, String s2){
        String longest = "";

        // generate all substrings of s1
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i+1 ; j <= s1.length(); j++) {
                String sub = s1.substring(i, j);

                // check if this substring is also in s2
                if (s2.contains(sub) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }
    static String longetsCommonSubStringOptimal(String s1, String s2){
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;
        int endIndex = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }

        return s1.substring(endIndex - maxLen, endIndex);
    }
    public static void main(String[] args) {
        String s1 = "abcdgh";
        String s2 = "acdghr";
        String result = longetsCommonSubStringBruteForce(s1, s2);
        String result1 = longetsCommonSubStringOptimal(s1, s2);

        System.out.println("Longest Common Substring: " + result + " (len = " + result.length() + ")");
        System.out.println("Longest Common Substring: " + result1 + " (len = " + result1.length() + ")");

    }
}
