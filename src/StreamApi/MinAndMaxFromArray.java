package StreamApi;

import java.util.Arrays;
import java.util.OptionalInt;

public class MinAndMaxFromArray {
    public static void main(String[] args) {
        int arr[]={3,5,9,8,1,4};
        OptionalInt max = Arrays.stream(arr).max();
        System.out.println(max.getAsInt());
        OptionalInt min = Arrays.stream(arr).min();
        System.out.println(min.getAsInt());

    }
}
