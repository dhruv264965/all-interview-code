package ARRAY;

import java.util.Arrays;

public class MaxDiffeenceBtTwoArray {
    static void maxDiffeenceBtTwoArrayBruteForce(int arr[]){
        int maxDiff=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]>arr[i]){
                    maxDiff=Math.max(maxDiff,arr[j]-arr[i]);
                }
            }
        }
        System.out.println(maxDiff);
    }
    static void maxDiffeenceBtTwoArrayOptimal(int arr[]){
        int maxDiff = arr[1] - arr[0];
        int minEle = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if((arr[i] - minEle) > maxDiff) {
                maxDiff = arr[i] - minEle;
            }
            if(arr[i] < minEle) {
                minEle = arr[i];
            }
        }
        System.out.println(maxDiff);
    }
    public static void main(String[] args) {
        //brute force
        int arr[] = {2,5,1, 15, 6, 4, 8, 9};
        maxDiffeenceBtTwoArrayBruteForce(arr);
        maxDiffeenceBtTwoArrayOptimal(arr);
    }
}
