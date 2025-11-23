package ARRAY;

import java.util.Arrays;

public class countOddSubarrays {
    static void countOddSubarraysBruteForce(int arr[]){
        int count =0;
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for (int j=i;j<arr.length;j++){
                sum+=arr[j];
                if(sum%2==1){
                    count++;
                    System.out.println(Arrays.toString(Arrays.copyOfRange(arr,i,j+1)));
                }
            }
        }
        System.out.println("Total count is "+count);
    }
    static void countOddSubarraysOptimal(int arr[]) {
        int evenCount = 1; // empty prefix (consider 0 as even)
        int oddCount = 0;
        int prefixSum = 0;
        int result = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                // odd subarrays can be formed with previous odd prefixes
                result += oddCount;
                evenCount++;
            } else {
                // odd subarrays can be formed with previous even prefixes
                result += evenCount;
                oddCount++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        countOddSubarraysBruteForce(arr);
        countOddSubarraysOptimal(arr);
    }
}
