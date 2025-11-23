package ARRAY;

import java.util.HashSet;

public class RemoveDuplicate {
    public static void main(String[] args) {
        int arr[]={3,4,5,6,7,8,4,6,1,2};
        HashSet<Integer>hp=new HashSet<>();
        for(int num:arr){
            hp.add(num);
        }
        System.out.println(hp);
        int result[] = new int[hp.size()];
        int index = 0;
        for (int num : hp) {
            result[index++] = num;
        }

        // Printing the result array
        System.out.print("Array after removing duplicates: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        // public int removeDuplicates(int[] nums) {
        //        HashSet<Integer> gp=new HashSet<>();
        //        for(int x:nums){
        //            gp.add(x);
        //        }
        //        int i = 0;
        //        for (int num : gp) {
        //            nums[i++] = num;
        //        }
        //
        //        // Return the count of unique elements
        //        return gp.size();
        //    }

    }
}
