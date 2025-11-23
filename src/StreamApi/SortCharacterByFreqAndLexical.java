package StreamApi;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortCharacterByFreqAndLexical {
    public static void main(String[] args) {
        String str = "traeeb";
        Map<Character, Long> freqMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Step 2: Sort by frequency (desc) then lexicographically
        String sorted = str.chars()
                .mapToObj(c -> (char) c)
                .sorted((a, b) -> {
                    int freqCompare = freqMap.get(b).compareTo(freqMap.get(a)); // desc freq
                    if (freqCompare == 0) {
                        return Character.compare(a, b); // lexicographic if freq same
                    }
                    return freqCompare;
                })
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(sorted); // eeabrt
    }
}
