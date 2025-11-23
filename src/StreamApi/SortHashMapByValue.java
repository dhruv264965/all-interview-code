package StreamApi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortHashMapByValue {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("B", 101);
        map.put("A", 104);
        map.put("C", 110);
        map.put("E", 100);
        map.put("D", 108);
        map.put("G", 108);  // remove replicate also

        LinkedHashMap<String, Integer> collect = map.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        System.out.println(collect);
    }
}
