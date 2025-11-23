package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectDuplicatePresent {
    public static void main(String[] args) {
     //   List<Integer> list=Arrays.asList(1,2,3,4,5,8,9,6,3,2,5);
        int arr[]={1,2,3,4,5,8,9,6,3,2,5};
        Map<Integer, Long> collect = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.entrySet().stream()
                .filter(x->x.getValue()>1)
                .forEach(x->System.out.println(x.getKey()));

    }
}
