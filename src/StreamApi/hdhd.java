package StreamApi;

import java.util.*;
import java.util.stream.Collectors;

public class hdhd {
    public static void main(String[] args) {
        List<String> str3= Arrays.asList("Java","Azure","Devops","Java","JavaFullStack","Pysparke","Azure","Japan","JavaScript");

        List<Map.Entry<String, Integer>> entries = str3.stream().map(w -> Map.entry(w, w.length()))
                .toList();
        System.out.println(entries);

        Map<Integer, List<String>> collect = str3.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(collect);

        Map<String, Integer> collect1 = str3.stream().collect(Collectors.toMap(
                x -> x,
                String::length,
                (e1,e2)->e1,
                LinkedHashMap::new
        ));
        System.out.println(collect1);

    }
}
