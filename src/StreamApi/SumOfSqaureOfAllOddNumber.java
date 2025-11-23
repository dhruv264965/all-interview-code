package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SumOfSqaureOfAllOddNumber {
    public static void main(String[] args) {
        List<Integer>list= Arrays.asList(2,7,4,3,5,9,6);
        int sum = list.stream()
                .filter(num -> num % 2 != 0) // filter odd number
                .map(i -> i * i)   // square all odd
                .reduce(0,Integer::sum); // find sum of all odd number . initially sum=0;

        System.out.println(sum);

        List<Integer> collect = list.stream()
                .filter(num -> num % 2 != 0) // filter odd number
                .map(i -> i * i)   // square all odd
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
