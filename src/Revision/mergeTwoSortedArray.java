package Revision;

import java.util.Arrays;

public class mergeTwoSortedArray {
    static int[] meregetwosortedArray(int arr1[],int arr2[],int n, int m,int ans[]){
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int i=0,j=0,k=0;
        // if we have element in both the arrays
        while (i<n && j<m){
            if(arr1[i]<arr2[j]){
                ans[k++]=arr1[i++];
            }
            else
            {
                ans[k++]=arr2[j++];
            }
        }
        // if one array 2 is empty
        while (i<n){
            ans[k++]=arr1[i++];
        }
        // if one array 1 is empty
        while (j<m){
            ans[k++]=arr2[j++];
        }

        return ans;
    }
    public static void main(String[] args) {
        int arr1[]={1,2,3,9};
        int arr2[]={6,16,18};
        int n= arr1.length;
        int m= arr2.length;
        int ans[]=new int[n+m];
        meregetwosortedArray(arr1,arr2,n,m,ans);
        System.out.println("merge two sorted array "+Arrays.toString(ans));


    }
}
