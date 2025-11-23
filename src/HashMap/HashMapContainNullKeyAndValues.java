package HashMap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapContainNullKeyAndValues {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 5);
        map.put("B", null);
        map.put("C", 2);
        map.put("D", 8);
        map.put("E", null);
        map.put("F", 1);
        map.put(null, 5);

        // ✅ Safe comparator for values (nulls last)
        Comparator<Map.Entry<String, Integer>> byValueNullSafe =
                Comparator.comparing(
                        Map.Entry<String, Integer>::getValue,
                        Comparator.nullsLast(Comparator.naturalOrder())
                );

        Map<String, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(byValueNullSafe)  // use custom comparator
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("\nSorted by Value (nulls last):");
        System.out.println(sortedMap);
    }
}
