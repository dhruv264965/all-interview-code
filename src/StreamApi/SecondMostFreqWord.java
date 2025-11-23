package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondMostFreqWord {
    public static void main(String[] args) {
        List<String>words= Arrays.asList("apple","banana","orange","apple","apple","orange");

       Map<String,Long>freqCount= words.stream().
               collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(freqCount);
        List<Map.Entry<String, Long>> collect = freqCount.entrySet().stream()
                .sorted((a, b) -> a.getValue().compareTo(b.getValue()))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());
        System.out.println(collect);

    }


}
