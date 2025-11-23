package Prefix;
//Input: nums = [2,-1,1,2], k = 2
//
//Output: 4
//Explanation: [2], [2,-1,1], [-1,1,2], [2] are the subarrays whose sum is equals to k.
public class SubArraySumEqualToK {
    static int subArraySumEqualToKBruteForce(int[] arr , int k){    // O(n^2)
        int count=0;
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for(int j=i;j<arr.length;j++){
                sum+=arr[j];
                if(sum==k){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int arr[]={2,-1,1,2};
        int k=2;
        System.out.println(subArraySumEqualToKBruteForce(arr,k));
    }
}
