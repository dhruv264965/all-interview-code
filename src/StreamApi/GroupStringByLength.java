package StreamApi;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupStringByLength {
    public static void main(String[] args) {
        List<String> words= Arrays.asList("a","bb","ccc", "dddd");
        Map<Integer, List<String>> collect = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(collect);
        Map<String, Integer> collect1 = words.stream().collect(Collectors.toMap(x -> x, String::length,(e1,e2)->e1, LinkedHashMap::new));
        System.out.println(collect1);

    }
}
// count wowel , longest string