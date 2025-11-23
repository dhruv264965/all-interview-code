package ARRAY;

public class LengthOfLongestIncreasingSubArray {
    public static int lengthOfLongestIncreasingSubArray(int arr[]){
        int len=1;
        int maxLen=1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                len++;
            }
            else {
                len=1;
            }
            maxLen=Math.max(len,maxLen);
        }
        return maxLen;
    }
    public static void printLongestIncreasingSubArray(int arr[]){
        int len=1;
        int maxLen=1;
        int start=0;   //This stores the start index of the current increasing sequence
        int maxStart=0;  // This stores the start index of the longest increasing subarray found.
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                len++;
            }
            else {
                len=1;
                start=i; //  // Reset start index for new increasing subarray and also kaha se break ho raha haipta chal jayega
            }
            if(len>maxLen){  //Update maxStart when len > maxLen: This ensures we remember where the longest sequence starts.
                maxLen=len;
                maxStart=start;   // Update maxStart when a longer subarray is found
            }
    }
        System.out.println("length Of Longest Increasing SubArray "+maxLen);
        for (int i=maxStart;i<maxStart+maxLen;i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void printLongestConsecutiveIncreasingSubArray(int arr[]){
        int len=1;
        int maxLen=1;
        int start=0;   //This stores the start index of the current increasing sequence
        int maxStart=0;  // This stores the start index of the longest increasing subarray found.
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1] && arr[i]==arr[i-1]+1){ // for consecutive increasing sub array
                len++;
            }
            else {
                len=1;
                start=i; //  // Reset start index for new increasing subarray and also kaha se break ho raha haipta chal jayega
            }
            if(len>maxLen){  //Update maxStart when len > maxLen: This ensures we remember where the longest sequence starts.
                maxLen=len;
                maxStart=start;   // Update maxStart when a longer subarray is found
            }
        }
        System.out.println("length Of Longest Consecutive Increasing SubArray "+maxLen);

        for (int i=maxStart;i<maxStart+maxLen;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String[] args) {
        int arr[]={2,3,4,6,10,11,15,3,4,5,8,9,10,2,10,11,1};
        System.out.println("-------length Of Longest Increasing SubArray------");
       System.out.println(lengthOfLongestIncreasingSubArray(arr));
        System.out.println("------Print Longest Increasing SubArray and length also-----");
         printLongestIncreasingSubArray(arr);
        System.out.println();
        System.out.println("----Print Longest Consecutive Increasing SubArray and length also----");
         printLongestConsecutiveIncreasingSubArray(arr);
    }
}
