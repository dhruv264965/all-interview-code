package Interview;

import java.util.Arrays;

public class MergeTwoSortedArray {
    public static void main(String[] args) {
        int arr[]={1,2,3,9};
        int arr1[]={6,16,18};
        Arrays.sort(arr);
        Arrays.sort(arr1);
        int i=0,j=0,k=0;
        int result[]=new int[arr.length + arr1.length];
        int n= arr.length;
        int m= arr1.length;
        while (i<n && j<m){
            if(arr[i]<arr1[j]){
                result[k++]=arr[i++];
            }
            else {
                result[k++]=arr1[j++];
            }
        }
        while (i<n){
            result[k++]=arr[i++];
        }
        while (j<m){
            result[k++]=arr1[j++];
        }
        for(int x:result){
            System.out.println(x);
        }
  // with 3rd array




    }
}
