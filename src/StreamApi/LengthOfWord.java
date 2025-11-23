package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LengthOfWord {
    public static void main(String[] args) {
        String str = "Big black bug bit a big black dog on his big black nose";
        // length of all word
        Map<String, Integer> collect = Arrays.stream(str.split(" ")).distinct()
                .collect(Collectors.toMap(
                        word -> word,
                        word -> word.length()
                ));
        System.out.println(collect);
        // word more than 3 length
        List<Map.Entry<String, Integer>> entries = collect.entrySet().stream()
                .filter(x -> x.getValue() > 3).toList();
        System.out.println(entries);

        // word more than 3 length
        List<String> longWords = Arrays.stream(str.split("\\s+")).distinct()
                .filter(word -> word.length() > 3)
                .collect(Collectors.toList());

        System.out.println("Words longer than 3 characters:");
        System.out.println(longWords);
    }
}
