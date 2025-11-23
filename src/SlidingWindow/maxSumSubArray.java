package SlidingWindow;
//Find the contiguous subarray (containing at least one number) that has the largest sum and return its sum.
//Example:
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.
public class maxSumSubArray {
//    public int maxSubArray(int[] nums){
//        return 0;
//    }
    public static void main(String[] args) {
        // brute force
         int result=0 ;
          int nums[] ={ -2,1,-3,4,-1,2,1,-5,4 };
          for(int i=0;i<nums.length;i++){
            int sum=0;
            for(int j=i;j<nums.length;j++){
                 sum+=nums[j];
                result=Math.max(result,sum);
            }
        }
        System.out.println(result);

        // optimal

        int[] nums1 = {-2,1,-3,4,-1,2,1,-5,4};
        int currSum=nums[0];
        int maxCum=nums[0];
        for(int i=1;i<nums1.length;i++){
            currSum=Math.max(nums1[i],currSum+nums1[i] );
            maxCum=Math.max(maxCum,currSum);
        }
        System.out.println(maxCum);
    }
}
