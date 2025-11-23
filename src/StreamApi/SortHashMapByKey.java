package StreamApi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortHashMapByKey {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 101);
        map.put("B", 104);
        map.put("C", 110);
        map.put("E", 100);
        map.put("D", 108);
        map.put("G", 108);  // remove replicate also
        LinkedHashMap<String, Integer> collect = map.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByKey().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        System.out.println(collect);

    }
}
