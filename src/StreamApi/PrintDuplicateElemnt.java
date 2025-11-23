package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintDuplicateElemnt {
    public static void main(String[] args) {
        //print duplicate element
        System.out.println("print duplicate element");
        List<Integer> list= Arrays.asList(10,20,30,40,20,30,30,30);
        Map<Integer, Long> collect = list.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect);
        List<Integer> duplicateElement = collect.entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println("Duplicate element is  "+ duplicateElement);

        //print unique element
        System.out.println("print unique element");
        List<Integer> list1= Arrays.asList(10,20,30,40,20,30);
        Map<Integer, Long> collect2 = list.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect2);
        List<Integer> collect3 = collect2.entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println(collect3);

    }
}
