package ARRAY;

public class ReverseArrayByKStepLeftRotate {
    static void rotate(int arr[], int i,int j){
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
        k=k%n;
        rotate(arr,0,k-1);  // 3 2 1
        for (int x:arr){
        //    System.out.print(x+" ");  //3 2 1 4 5 6 7
        }
      //  System.out.println();
        rotate(arr,k,n-1); // 7 6 5 4    combne=3 2 1 7 6 5 4
        for (int x:arr){
         //   System.out.print(x+" ");  // 3 2 1 7 6 5 4
        }
       // System.out.println();
        rotate(arr,0,n-1);  // 4 5 6 7 1 2 3
        for (int x:arr){
            System.out.print(x+" "); //4 5 6 7 1 2 3
        }
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7};
        int k=3; // the output is =  4 5 6 7 1 2 3
        int n= arr.length;
        int i=0;
        int j= arr.length-1;
        rotateInPlace(arr,k,n);
    }
}
