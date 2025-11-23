package StreamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicateInteger {
    public static void main(String[] args) {
        Integer[] arr={1, 2, 2, 3, 4, 4, 5};

        // store in array
        Integer[] duplicate = Arrays.stream(arr)
                .distinct()
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(duplicate));

        // store in list
        List<Integer> collect = Arrays.stream(arr)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);


        // Original array with duplicates
        int[] a = {1, 2, 2, 3, 4, 4, 5,7,9,7};

        // Convert int[] to List<Integer> and remove duplicates
        List<Integer> uniqueList = Arrays.stream(a)                // Convert array to IntStream
                .distinct()                  // Remove duplicates
                .boxed()                     // Convert each int to Integer
                .collect(Collectors.toList()); // Collect as List<Integer>

        // Print the unique List
        System.out.println(uniqueList);
    }
}
