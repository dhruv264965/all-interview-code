package StreamApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciSeries {
    public static void main(String[] args) {
        List<Integer> collect = Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .limit(10)
                .map(f -> f[0])
                .collect(Collectors.toList());
        System.out.println(collect);

  // another appraaoch
        int n = 10;
        List<Integer> fibList = new ArrayList<>();
        fibList.add(0);
        fibList.add(1);

        IntStream.range(2, n).forEach(i ->
                fibList.add(fibList.get(i - 1) + fibList.get(i - 2))
        );
        System.out.println(fibList);

//        for(int i=2;i<n;i++){    // by using for loop also we can write
//            fibList.add(fibList.get(i-1)+fibList.get(i-2));
//
//        }
//        System.out.println(fibList);
    }
}
