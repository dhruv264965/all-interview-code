package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InteractionOfTwoArray {
    public static void main(String[] args) {
        int nums1[]={4,9,5};
        int nums2[]={9,4,9,8,4};
        Set<Integer> data = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

        List<Integer> list = Arrays.stream(nums2).boxed().filter(data::contains).distinct().toList();
        System.out.println(list);
    }
}
