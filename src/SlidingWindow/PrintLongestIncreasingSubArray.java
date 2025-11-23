package SlidingWindow;

public class PrintLongestIncreasingSubArray {
    public static void main(String[] args) {
        int length =1 ,  maxLength=1;
        int start=0 , maxStart=0;   // to keep the track of index where we got max length . so if we want to print
                             // increaing sub array . the index we got and add max length travsal and print
        int arr[]= {2,3,10,11,15,3,4,5,8,9,10,2,10,11,1 };
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                length++;
            }
            else {
                length=1;
                start=i;
            }
            if(length>maxLength){
                maxLength=length;
                maxStart=start;
            }
        }
        for(int i=maxStart; i<maxStart+maxLength;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.println(maxLength);
    }
}
