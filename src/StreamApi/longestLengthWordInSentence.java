package StreamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class longestLengthWordInSentence {
    public static void main(String[] args) {
        String str="I am Leaning Stream Api in java";
        String ans=Arrays.stream(str.split(" "))
                .max((m1,m2)->m1.length()-m2.length())
                .orElse(" ");
        System.out.println(ans);

        String s = Arrays.stream(str.split(" "))
                .max(Comparator.comparing(String::length)).get();
        System.out.println(s);


    }
}
