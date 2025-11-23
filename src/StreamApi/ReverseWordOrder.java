package StreamApi;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class ReverseWordOrder {
    public static void main(String[] args) {
        String input = "Java is fun";
        String collect = Arrays.stream(input.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        List -> {
                            Collections.reverse(List);
                            return String.join(" ", List);
                        }
                ));
        System.out.println(collect);    }
}
