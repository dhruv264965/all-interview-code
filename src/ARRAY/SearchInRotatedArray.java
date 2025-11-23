package ARRAY;

import java.util.Arrays;

//You are given a rotated sorted array and a target value. Write a function to search for the target in
//O(logn) time. If it exists, return its index. Otherwise, return -1
//Example:
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
public class SearchInRotatedArray {
    public static int search(int[] nums, int target){
        // if array is sorted
/*        Arrays.sort(nums);
       int low=0;
       int high=nums.length-1;     // arr= 1,2,3,4,5,6,7,8   target =7
       while (low<=high){
           int mid=low+(high-low)/2;
           if(nums[mid]==target) return mid;
           if(nums[mid]>target){
               high=mid-1;
           }
           else {
               low=mid+1;
           }
       }
       return -1;                                */
        //if array is not sorted but it is rotated means half of the part is sort
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if (nums[mid] == target) return mid;
            // if left is sorted
            if(nums[low]<=nums[mid]) {
                if (target>=nums[low] && target<nums[mid]) {   //[4,5,6,7,0,1,2], target = 5
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
                // if right is sorted
            }
                else {
                    if(target>nums[mid] && target<=nums[high]){   //[4,5,6,7,0,1,2], target = 5
                        low=mid+1;
                    }
                    else {
                        high=mid-1;
                    }

            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums={ 7,8,9,0,1,2,3,4,5,6 };
       int target =6;
       int result=search(nums,target);
        System.out.println(result);
    }
}
