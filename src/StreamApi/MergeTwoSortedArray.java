package StreamApi;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MergeTwoSortedArray {
    public static void main(String[] args) {
        int arr1[]={1,4,6};
        int arr2[]={2,5,11};
        int[] ints = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .sorted()
                .toArray();
        System.out.println(Arrays.toString(ints));

    }
}
