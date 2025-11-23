package HashMap;

import java.util.HashMap;

public class TwoSum {
    static int[] twoSumBruteForce(int[] nums ,int target){
        int ans[]=new int[2];
        for(int i=0;i< nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                if(nums[i]+nums[j]==target){
                    ans[0]=i;
                    ans[1]=j;
                    return ans;
                }
            }
        }
        return ans;
    }
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int ans[]=new int[2];
        ans[0]=ans[1]=-1;
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parter = target - nums[i];
            if(mp.containsKey(parter)){
                ans[0]=mp.get(parter);
                ans[1]=i;
                return ans;

            }
            mp.put(nums[i],i);

        }
        return ans;
    }

    public static void main(String[] args) {
       // int n = 5;
        int[] nums = {1,2,1,3,2};
        int target = 4;
        int[] ans=twoSumBruteForce(nums,target);
        System.out.println("["+ans[0]+" "+ans[1]+"]");



    }
}
//        int temp[]=new int[2];
//        for(int i=0;i<nums.length;i++)
//        {
//            for(int j=i+1;j<nums.length;j++)
//            {
//                if(nums[i]+nums[j]==target)
//                {
//                    temp[0]=i;
//                    temp[1]=j;
//                }
//            }
//        }
//        return temp;
//    }
