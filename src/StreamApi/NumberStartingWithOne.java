package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberStartingWithOne {
    public static void main(String[] args) {
        int arr[]={10,157,56,421,65,135};
        List<Integer> list = Arrays.stream(arr).filter(num -> String.valueOf(num).startsWith("1")).boxed().toList();

        System.out.println(list);
        System.out.println("------------------------------------------");

        List<Integer>input=Arrays.asList(10,157,56,421,65,135);
        List<Integer> collect1 = input.stream()
                .filter(num -> String.valueOf(num).startsWith("1"))
                .collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("------------------------------------------");

        List<Integer>input1=Arrays.asList(10,157,56,421,65,135);
        List<Integer> collect11 = input.stream()
                .filter(num -> Integer.toString(num).startsWith("1"))
                .collect(Collectors.toList());
        System.out.println(collect11);
    }
}
