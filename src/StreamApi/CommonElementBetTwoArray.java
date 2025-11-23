package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonElementBetTwoArray {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = {3, 4, 5, 6};
        Set<Integer> collect = Arrays.stream(b).boxed().collect(Collectors.toSet());
        System.out.println(collect);
        List<Integer> list = Arrays.stream(a).boxed().filter(collect::contains).toList();
        System.out.println(list);
    }
}
