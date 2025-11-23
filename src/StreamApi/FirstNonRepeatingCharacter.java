package StreamApi;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String str="swiss";
        String collect = Arrays.stream(str.split(""))//Converts the array into a Stream of Strings.
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                    //Groups identical characters and counts them.Stores the result in a LinkedHashMap, which preserves order.
                .entrySet().stream()//Converts the LinkedHashMap to a Stream of Map Entries (Map.Entry<Character, Long>).
                .filter(entry->entry.getValue()==1)
                .map(entry->entry.getKey())
                .findFirst()
                .orElse(null);
        System.out.println(collect);

        // Another approch
        Character firstNonRepeating = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);

        System.out.println(firstNonRepeating);


    }
}
