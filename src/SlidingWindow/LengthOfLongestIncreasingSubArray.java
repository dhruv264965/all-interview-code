package SlidingWindow;

public class LengthOfLongestIncreasingSubArray {
    public static void main(String[] args) {
        int length =1 ,  maxLength=1;
        int arr[]= {2,3,10,11,15,3,4,5,8,9,10,2,10,11,1 };
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                length++;
            }
            else {
                length=1;
            }
            if(length>maxLength){
                maxLength=length;
            }
        }

        System.out.println(maxLength);
    }
}
