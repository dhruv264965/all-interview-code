package ARRAY;

public class MissingArray {
    static int missingNumber(int arr[]){
        int n= arr.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        int number=arr[arr.length-1];
        int numberSum=(number*(number+1))/2;
        int missingNumber=numberSum-sum;
       return missingNumber;
    }
  static   int missingNumberr(int[] arr) {
         int res= arr.length;
         for(int i=0;i< arr.length;i++){
             res+=i-arr[i];
         }
         return res;
    }
    public static void main(String[] args) {
        int arr[]={0,1,3};
        System.out.println(missingNumber(arr));
        System.out.println(missingNumberr(arr));
    }
}
