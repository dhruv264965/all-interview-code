package ARRAY;

public class LargestElementInTheArray {
    static int lagestElement(int arr[]){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int arr[]={4,2,66,43,2,66,99,6,4};
        System.out.println(lagestElement(arr));
    }
}
