package HashMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepCharacter {
    public static void main(String[] args) {
        String str = "aaswiwssb";

        // Traditional approach
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        System.out.println(map);
        for (char ch : str.toCharArray()) {
            if (map.get(ch) == 1) {
                System.out.println("Traditional: " + ch);
                break;
            }
        }

        // Java 8 Stream API approach
        Character result = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("Stream API: " + result);
    }
}
