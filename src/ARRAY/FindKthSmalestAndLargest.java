package ARRAY;

import java.util.Arrays;

public class FindKthSmalestAndLargest {
    static int[] findKthSmalestAndLargest(int arr[],int k){
        Arrays.sort(arr);
        int ans[]={arr[k-1],arr[arr.length-k]};
        return ans;
    }
    public static void main(String[] args) {
        int arr[]={5,8,1,3,6,2,10,7,15};
        int k=4;
        int result[]=findKthSmalestAndLargest(arr,k);
        System.out.println("["+result[0]+","+result[1]+"]");
    }
}
