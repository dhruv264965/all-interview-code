package ARRAY;

import java.util.Arrays;
//A deep copy creates a new array and copies each element individually.
//Changes in the copied array don’t affect the original.
public class DeepCopy {
    static void printArray(int arr[]){
        int n= arr.length;
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int arr1[]={1,2,3,4,5,6};      //only reference is created for arr2 . No new memory created in heap.
        printArray(arr1);
        // how to copy value in arr2 and make new memory
        int arr2[]=arr1.clone();  //  new memory in the heap with different address. so if we want to change in arr2
//        int arr2[]= Arrays.copyOf(arr1,arr1.length);// new memory in the heap with different address. so if we want to change in arr2
        arr2[0]=0;               // if we change arr2 only arr2 is changes and arr1 is unchanged
        arr2[1]=0;
        printArray(arr2);
        printArray(arr1);
    }
}
