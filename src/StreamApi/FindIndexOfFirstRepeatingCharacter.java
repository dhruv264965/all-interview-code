package StreamApi;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindIndexOfFirstRepeatingCharacter {
    public static void main(String[] args) {
        String str="dhruvru";
        Optional<Map.Entry<Character, Long>> first = str.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .findFirst();
        System.out.println(first);
        if(first.isPresent()){
            Map.Entry<Character,Long>entry=first.get();
            char unique= entry.getKey();
            int index =str.indexOf(unique);
            System.out.println(unique+" "+ index);
        }
    }
}
