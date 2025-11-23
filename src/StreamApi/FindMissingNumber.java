package StreamApi;

import java.util.Arrays;

public class FindMissingNumber {
    public static void main(String[] args) {
        // if array start with 0
        int[] arr = {0, 1, 2, 4, 5};
        int n= arr.length;
        int expectedSum= n*(n+1)/2;
        int actualSum=Arrays.stream(arr).sum();
        int missing = expectedSum - actualSum;
        System.out.println(missing);  // 3

        // if array start with 1
        int[] arr1 = {1, 2, 4, 5};
        int nn= arr1.length+1;
        int expectedSumm= nn*(nn+1)/2;
        int actualSumm=Arrays.stream(arr1).sum();
        int missingg = expectedSumm - actualSumm;
        System.out.println(missingg);  // 3

    }
}
