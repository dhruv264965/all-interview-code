package ARRAY;

public class SecondSmallest {
    static int secondSmallest(int arr[]){
        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){ //3,5,2,7,6,9
            if(arr[i]<min1){
                min2=min1;
                min1=arr[i];
            }
            if(arr[i]<min2 && arr[i]!=min1){
                min2=arr[i];
            }
        }
        return min2;
    }
    public static void main(String[] args) {
        int arr[]={3,5,2,7,6,9};
        System.out.println(secondSmallest(arr));
    }
}
