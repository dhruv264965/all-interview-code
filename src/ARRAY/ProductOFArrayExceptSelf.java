package ARRAY;

import java.util.ArrayList;
import java.util.List;

//Given an array nums n integers, return an array answer such that answer[i] is equal to the product of all the elements of
// nums except nums[i]
//You must solve the problem without division and in O(n).
public class ProductOFArrayExceptSelf {
//    public int[] productExceptSelf(int[] nums){
//        return new int[nums];
//    }

    public static void main(String[] args) {   //[48,24,12,8]
        //brute force approach
        int num[] = {1, 2, 4, 6};
        int n=num.length;
        int res[]=new int[n];
        for(int i=0;i< num.length;i++){
            int prod=1;
            for(int j=0;j<n;j++){
                if(i!=j){
                    prod*=num[j];
                }
            }
                res[i]=prod;

        }
        for(int x:res) {
            System.out.print(x+" ");
        }
    }
}
