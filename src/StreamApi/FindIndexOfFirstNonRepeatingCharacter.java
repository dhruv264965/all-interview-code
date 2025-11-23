package StreamApi;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindIndexOfFirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String str="dhekdh";
        Optional<Map.Entry<Character, Long>> first = str.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(a -> a.getValue() == 1)
                .findFirst();
        System.out.println(first.get());
        // aprroach 1 to find index

//        System.out.println(first);
//        first.ifPresent(entry->{
//            char ch = entry.getKey();
//            int index= IntStream.range(0,str.length())
//                    .filter(i->str.charAt(i)==ch)
//                    .findFirst().orElse(-1);
//            System.out.println(index);
//        });

      //  aprroach 1 to find index
        if (first.isPresent()){
            Map.Entry<Character,Long>characterLongEntry=first.get();
            char uniqueChar= characterLongEntry.getKey();
            int index=str.indexOf(uniqueChar);
            System.out.println(uniqueChar+ " "+ index);
        }
    }
}
