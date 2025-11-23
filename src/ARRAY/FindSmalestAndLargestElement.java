package ARRAY;

import java.util.Arrays;

public class FindSmalestAndLargestElement {
    static int[] findSmalestAndLargest(int arr[]){
        Arrays.sort(arr);
        int ans[]={arr[0],arr[arr.length-1]};
        return ans;
    }
    public static void main(String[] args) {
        int arr[]={5,8,1,3,6,2};
        int result[]=findSmalestAndLargest(arr);
        System.out.println("["+result[0]+","+result[1]+"]");

    }
}
