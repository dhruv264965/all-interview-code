package JAVA8Features;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseString {
    public static void main(String[] args) {
        String str="Dhruv";
        String[] words=str.split(" ");
        List<String>reverseString =Arrays.stream(words)
                .map((word)->
                        new StringBuilder(word).reverse().toString())
                .collect(Collectors.toList());
        System.out.println(reverseString);


    }
}
