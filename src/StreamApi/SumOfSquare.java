package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SumOfSquare {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int SumOfSquare = list.stream()
                .filter(num -> num % 2 == 0)
                .map(x -> x * x)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(SumOfSquare);

    }
}
