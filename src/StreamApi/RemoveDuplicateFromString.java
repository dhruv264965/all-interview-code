package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicateFromString {
    public static void main(String[] args) {
        String str= "debacadef";
        // method 1
        List<String> collect = Arrays.stream(str.split(""))
                .distinct().collect(Collectors.toList());
        System.out.println(collect);  // [d, e, b, a, c, f]

       // method 2
        str.chars()
                .distinct().mapToObj(x->(char)x ).forEach(System.out::print);

        System.out.println();
        Arrays.stream(str.split(""))
                .distinct().forEach(System.out::print);

    }
}
