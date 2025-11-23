package STRING;

import java.util.Arrays;

public class StringRegrex {
    public static void main(String[] args) {
        String input = "abc123xyz";
        String digits = input.replaceAll("\\D+", ""); // Remove non-digits
        System.out.println(digits); // Output: 123

        String input1 = "abc123xyz";
        String letters = input1.replaceAll("\\d+", ""); // Remove digits
        System.out.println(letters); // Output: abcxyz

        String sentence = "Hello@ world! Java& 123.";
        String s=sentence.replaceAll("\\W"," ");
        System.out.println(s);
        String[] words = sentence.split("\\W+"); // Splits on non-word characters
        System.out.println(Arrays.toString(words)); // Output: [Hello, world, Java, 123]

        String sentence1 = "Hello@ world! Java& 123.";
       String s1= sentence1.replaceAll("\\w"," ").trim();
        System.out.println(s1);
        String[] words1 = sentence.split("\\w+"); // Splits on non-word characters
        System.out.println(Arrays.toString(words1)); // Output: [Hello, world, Java, 123]


    }
}
