package ARRAY;
//A shallow copy creates a new reference to the same memory location as the original array.
//Changes in one array affect the other.
public class ShallowCopyArray {
    static void printArray(int arr[]){
        int n= arr.length;
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) { // no new memory is created
        int arr1[]={1,2,3,4,5,6};      //only reference is created for arr2 . No new memory created in heap.
        printArray(arr1);
        int arr2[]=arr1;
        arr2[0]=0;
        arr2[1]=0;
        printArray(arr2); // 0 0 3 4 5 6
        printArray(arr1);  // 0 0 3 4 5 6 - we only change in arr2 but it reflect in arr1 also

    }
}
