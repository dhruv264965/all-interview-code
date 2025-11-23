package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstCharStartWithString {
    public static void main(String[] args) {
        String str="apple banana mango box been fox auto bike";
        List<String> collect = Arrays.stream(str.split(" "))
                .filter(ch -> ch.startsWith("b"))
                .collect(Collectors.toList());
        System.out.println(collect);
        // number start with 1
        List<Integer>list=Arrays.asList(10,21,45,1,100,15,65);
        List<Integer> list1 = list.stream()
                .filter(num -> String.valueOf(num).startsWith("1"))
                .toList();
        System.out.println(list1);

        // number not start with 1
        List<Integer>list2=Arrays.asList(10,21,45,1,100,15,65);
        List<Integer> list3 = list2.stream()
                .filter(num -> !String.valueOf(num).startsWith("1"))
                .toList();
        System.out.println(list3);
    }

}
