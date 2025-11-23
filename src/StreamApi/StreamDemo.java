package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer>number= Arrays.asList(1,2,3,4,5,6);
      //  number.stream().filter(x->x%2==0).collect();
        System.out.println(number.stream().filter(x->x%2==0).count());

        //Creating Stream
        // 1. Collection 
        List<Integer>list= Arrays.asList(1,2,3,4,5,6);
        Stream<Integer> stream = list.stream();
        // 2. Array
        String[] arr={"a","b","c"};
        Stream<String> stream1 = Arrays.stream(arr);
        
        // 3. Stream.of()
        Stream<String> a = Stream.of("a", "b", "c");

        // 4 .Infinite Stream
        Stream<Integer> generate = Stream.generate(() -> 1);
        List<Integer> limit = Stream.iterate(1, x -> x + 1).limit(100).collect(Collectors.toList());
        System.out.println(limit);

        // 5 .
    }
}
