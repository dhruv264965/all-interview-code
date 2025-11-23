package StreamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperation {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4);
        // 1. collect
        System.out.println("Collect");
        List<Integer> collect = list.stream().collect(Collectors.toList());
        List<Integer> collect1 = list.stream().skip(1).toList();
        System.out.println(collect);
        System.out.println(collect1);

        //2. forEach
        System.out.println("forEach");
        list.stream().forEach(x-> System.out.println(x));

        // 3. reduce
        System.out.println("reduce");
        Optional<Integer> optionalInteger = list.stream().reduce((x, y) -> x + y);
        System.out.println(optionalInteger.get());

        // 4. count
        System.out.println("count");
        long count = list.stream().skip(1).count();
        System.out.println(count);

        // 5. anymatch , allMatch , noneMatch - return boolean result, circuit operation(ones get excuted return the result not thinking about other operati
        System.out.println("anymatch , allMatch , noneMatch");
        boolean b = list.stream().anyMatch(x -> x % 2 == 0);
        System.out.println(b);
        boolean b1 = list.stream().allMatch(x -> x > 0);
        System.out.println(b1);
        boolean b2 = list.stream().noneMatch(x -> x < 0);
        System.out.println(b2);

        // 6. findFirst , findAny. - circuit operation(ones get excuted return the result not thinking about other operation
        System.out.println("findFirst , findAny");
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // 7. toArray()

        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min / max
        System.out.println("max: " + Stream.of(2, 44, 69).max((o1, o2) -> o2 - o1));
        System.out.println("min: " + Stream.of(2, 44, 69).min(Comparator.naturalOrder()));
        System.out.println("------  Example  --------");
        // 7. Filtering and Collecting Names
        System.out.println("Filtering and Collecting Names");
        List<String>names=Arrays.asList("Anna","Bob","charlie","David");
        System.out.println(names.stream().filter(x->x.length()>3).toList());

        // 8 .Squaring and Sorting number
        System.out.println("Squaring and Sorting number");
        List<Integer> numbers=Arrays.asList(5,2,9,1,6);
        System.out.println(numbers.stream().map(x->x*x).sorted().toList());

        // 9 . Find occurrence of character
        System.out.println("Find occurrence of character");
        String str="dhdruv";
        System.out.println(str.chars().filter(x->x=='d').count());

        // 10. Summing the number
        System.out.println("Summing the number");
        List<Integer> sum=Arrays.asList(5,2,9,1,6);
        System.out.println(sum.stream().reduce((x,y)->x+y).get());
    }
}
