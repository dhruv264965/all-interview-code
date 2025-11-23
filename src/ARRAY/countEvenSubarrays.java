package ARRAY;

import java.util.Arrays;

public class countEvenSubarrays {
   static void countEvenSubarraysBruteForce(int arr[]){
        int count =0;
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for (int j=i;j<arr.length;j++){
                sum+=arr[j];
                if(sum%2==0){
                    count++;
                    System.out.println(Arrays.toString(Arrays.copyOfRange(arr,i,j+1)));
                }
            }
        }
        System.out.println("Total count is "+count);
    }
    static void countEvenSubarraysOptimal(int arr[]){
        int evenCount = 1; // empty prefix  // we are considering 0 as even
        int oddCount = 0;
        int prefixSum = 0;
        int result = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                result += evenCount;
                evenCount++;
            } else {
                result += oddCount;
                oddCount++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        countEvenSubarraysBruteForce(arr);
        countEvenSubarraysOptimal(arr);
    }
}
// Input:
//arr = {1, 2, 3}
//
//
//We want to count odd-sum subarrays.
//
//Initialization:
//evenCount = 1   // prefix sum = 0 (empty prefix is even)
//oddCount  = 0
//prefixSum = 0
//result    = 0
//
//Step 1: num = 1
//prefixSum = 0 + 1 = 1   (odd)
//
//
//If prefixSum is odd → odd subarrays can be formed with previous even prefixes.
//
//evenCount = 1 → result += 1 → result = 1
//
//Increase oddCount → oddCount = 1
//
//State:
//evenCount = 1
//oddCount  = 1
//result    = 1
//
//
//✅ Subarrays counted: [1] (odd sum)
//
//Step 2: num = 2
//prefixSum = 1 + 2 = 3   (odd)
//
//
//prefixSum is odd → odd subarrays form with previous even prefixes
//
//evenCount = 1 → result += 1 → result = 2
//
//oddCount++ → oddCount = 2
//
//State:
//evenCount = 1
//oddCount  = 2
//result    = 2
//
//
//✅ Subarrays counted: [2,3], [1,2]
//(current subarray sum odd ones)
//
//Step 3: num = 3
//prefixSum = 3 + 3 = 6   (even)
//
//
//prefixSum is even → odd subarrays form with previous odd prefixes
//
//oddCount = 2 → result += 2 → result = 4
//
//evenCount++ → evenCount = 2
//
//State:
//evenCount = 2
//oddCount  = 2
//result    = 4
//
//
//✅ Subarrays counted: [3], [1,2,3]
//
//Final Result:
//result = 5