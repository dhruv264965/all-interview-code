package ARRAY;

import java.util.Arrays;

public class FindEqilibriumIndex {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 2};
        int totalSum= Arrays.stream(arr).sum();
        int leftSum=0;
        for(int i=0;i<arr.length;i++){
            totalSum-=arr[i];
            if(totalSum==leftSum){
                System.out.println(" Equilibrium index "+i);
                break;
            }
            leftSum+=arr[i];
        }
    }
}
