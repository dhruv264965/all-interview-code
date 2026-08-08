package DSA150Question.Array;
// Contains Duplicate

//Given an integer array nums, return true if any value appears more than once in the array, otherwise
// return false.
//
//Example 1:
//Input: nums = [1, 2, 3, 3]
//Output: true

import java.util.HashSet;

//Example 2:
//Input: nums = [1, 2, 3, 4]
//Output: false
public class ContainDuplicate {

    static Boolean containDuplicateBruteForce(int arr[]){
        //Time complexity: 0(n^2)
        //Space complexity: 0(1)

        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j< arr.length;j++){
                if(arr[i]==arr[j])
                {
                    return true;
                }
            }
        }
        return false;
    }
    static Boolean containDuplicateOptimal(int arr[]){
        // Time complexity:
        //O(n)
        //Space complexity:
        //O(n)
        HashSet<Integer> hp= new HashSet<>();
        for(int x:arr){
            if(!hp.contains(x)){
                return true;
            }
        }
        return false;
    }
    static void PrintDuplicate(int arr[]){
        HashSet<Integer>hp = new HashSet<>();
        HashSet<Integer>dup = new HashSet<>();
        for(int x :arr){
            if(!hp.add(x)){
                dup.add(x);
            }
        }
        System.out.println(dup);
    }
    public static void main(String[] args) {
        int arr[]={1, 2, 3, 3};
       // System.out.println(containDuplicateBruteForce(arr));
       // System.out.println(containDuplicateOptimal(arr));
        PrintDuplicate(arr);
    }
}
