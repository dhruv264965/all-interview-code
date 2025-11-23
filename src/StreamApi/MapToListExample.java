package StreamApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapToListExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);
        List<String> keyList = map.keySet().stream()
                .collect(Collectors.toList());
        System.out.println("List of Keys: " + keyList);

        // Convert values to List using Java 8
        List<Integer> valueList = map.values().stream()
                .collect(Collectors.toList());
        System.out.println("List of Values: " + valueList);

        // Convert entries to List using Java 8
        List<Map.Entry<String, Integer>> entryList = map.entrySet().stream()
                .collect(Collectors.toList());
        System.out.println("List of Entries:");
        entryList.forEach(entry ->
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue())
        );

        // BONUS: Convert to a list of custom strings
        List<String> customList = map.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.toList());
        System.out.println("Custom Key=Value List: " + customList);
    }
}
