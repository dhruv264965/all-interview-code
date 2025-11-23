package ARRAY;

public class MoveAllZeroToRightSide {
    static void moveAllZeroRightSideWithExtraArray(int arr[],int n,int k,int ans[]){
        for(int x:arr){
            if(x>0){
                ans[k++]=x;
            }
        }
        for (int x:ans)
            System.out.print(x+" ");
    }
    static void moveAllZeroRightSide(int arr[],int n,int k){
        // Move non-zero elements to the left
        for (int i = 0; i < n; i++) {   //   int arr[]={1,0,5,7,0,6,0};
            if (arr[i] != 0) {
                arr[k++] = arr[i];
            }
        }

        // Fill the remaining positions with zeros
        while (k < n) {
            arr[k++] = 0;
        }

        // Print the result
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
    public static void main(String[] args) {
        int arr[]={1,0,5,7,0,6,0};
        int n=arr.length;
        int k=0;
        int ans[]=new int[n];
       // moveAllZeroRightSideWithExtraArray(arr,n,k,ans);
        moveAllZeroRightSide(arr,n,k);
    }
}
