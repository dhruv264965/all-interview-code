package ARRAY;

public class ReverseArrayByKStepRightRotate {
static void reverse(int arr[], int i, int j){


    while (i<j){
        swap(arr,i,j);
        i++;
        j--;
    }
}
static void swap(int arr[],int i,int j){

       int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    static void rotateInPlace(int arr[],int k,int n){
        // Handle cases where k > n
        k = k % n;
         reverse(arr,0,n-k-1);  //4 3 2 1 - > 4 3 2 1 5 6 7
         reverse(arr,n-k,n-1); // 7 6 5  ->   4 3 2 1 7 6 5
         reverse(arr,0,n-1);   // 5 6 7 1 2 3 4
         for(int x:arr)
        System.out.print(x+" ");
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7};
        int k=3; // the output is =  5 6 7 1 2 3 4
        int n= arr.length;
        int i=0;
        int j= arr.length-1;
         rotateInPlace(arr,k,n);
}
}
