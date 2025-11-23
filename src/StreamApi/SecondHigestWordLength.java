package StreamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SecondHigestWordLength {
    public static void main(String[] args) {
        String str="I am Leaning Stream Api in java";
        String s = Arrays.stream(str.split(" "))

                .sorted(Comparator.comparing(String::length).reversed()).skip(1).findFirst().get();
        System.out.println(s);

        Integer integer = Arrays.stream(str.split(" ")).map(x -> x.length()).sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(integer);


    }
}
