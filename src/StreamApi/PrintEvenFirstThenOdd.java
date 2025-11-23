package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintEvenFirstThenOdd {
    public static void main(String[] args) {
        Integer[] arr={1,2,3,4,5,6,7,8,9};
        List<Integer> number = Arrays.stream(arr).collect(Collectors.toList());
        List<Integer> collect = Stream.concat(number.stream().filter(n -> n % 2 == 0), number.stream().filter(n -> n % 2 != 0)).collect(Collectors.toList());
        System.out.println(collect);

        int ar[]={1,2,3,4,5,6,7,8,9};
        List<Integer>list=Arrays.stream(ar).boxed().collect(Collectors.toList());
        List<Integer> col = Stream.concat(list.stream().filter(n -> n % 2 == 0), list.stream().filter(n -> n % 2 != 0)).collect(Collectors.toList());
        System.out.println(col);





    }
}
