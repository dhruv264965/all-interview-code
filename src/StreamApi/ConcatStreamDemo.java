package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ConcatStreamDemo {
    public static void main(String[] args) {
        List<Integer>list= Arrays.asList(1,5,3,4,2);
        List<Integer>list1= Arrays.asList(6,78,9,99);
        Stream<Integer> concat = Stream.concat(list.stream(), list1.stream());
        concat.forEach(x-> System.out.print(x+" "));
        System.out.println();
        Stream<Integer> sortConcat = Stream.concat(list.stream().sorted(), list1.stream().sorted());
        sortConcat.forEach(x-> System.out.print(x+" "));

    }
}
