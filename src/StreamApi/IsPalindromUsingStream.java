package StreamApi;

import java.util.stream.IntStream;

public class IsPalindromUsingStream {
    public static void main(String[] args) {
        String str = "madam";
        int n = str.length();
        boolean b = IntStream.rangeClosed(0, n / 2).anyMatch(i -> str.charAt(i) == str.charAt(n - 1 - i));
        System.out.println(b);

        // int n = str.length();
        boolean b1 = IntStream.range(0, n / 2)
                .allMatch(i -> str.charAt(i) == str.charAt(n - 1 - i));
        System.out.println(b1);
    }
    }
