package StreamApi;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaxFreqLexicalChar {
    public static void main(String[] args) {
        String str = "frolvmacsertghfcsxzaqwgjknbuxncjewifl";

        // Step 1: Frequency map
        Map<Character, Long> freqMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
        System.out.println(freqMap);

        // Step 2: Max frequency with lexical tie-breaker
        Optional<Map.Entry<Character, Long>> result = freqMap.entrySet().stream()
                .sorted(Comparator
                        .comparing(Map.Entry<Character, Long>::getValue, Comparator.reverseOrder())  // Sort by freq desc
                        .thenComparing(Map.Entry::getKey)  // Tie-breaker: lexicographically smallest char
                )
                .findFirst();

        // Step 3: Print result
        result.ifPresent(entry ->
                System.out.println("Max frequency char (lexical): " + entry.getKey() + " => " + entry.getValue()));
    }
}
