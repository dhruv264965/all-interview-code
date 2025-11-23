package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FreqOfEachCharacter {
    public static void main(String[] args) {
        String str="Dhruv Shukla";
        Map<String, Long> collect = Arrays.stream(str.toLowerCase().split("")) //Groups the characters by their identity (Function.identity()).
              //  .filter(ch->!ch.isBlank()) //exclude white space
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); //Function.identity mai jo hum denge wahi return  kar dega
        System.out.println(collect);
      // sort character by freqency
        List<Map.Entry<String,Long>>sortFreq=collect.entrySet()
                .stream()
                .sorted((a,b)->b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toList());
        System.out.println(sortFreq);

        // Return duplicate character
        List<String> entries = sortFreq.stream()
                .filter(x -> x.getValue() > 1)
                .map(a->a.getKey())
                .toList();
        System.out.println(entries);
    }
}
