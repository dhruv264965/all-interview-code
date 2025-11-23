package ARRAY;

public class ReverseArray {
    static int [] reverseArray(int arr[]){
        int n= arr.length;
        int ans[]=new int[n];
        int j=0;
        for(int i=n-1;i>=0;i--){
            ans[j++]=arr[i];
        }
        return ans;
    }
    static void reverse(){
        int[] arr = {1, 2, 3, 4, 5};

        int left = 0, right = arr.length - 1;
        while (left < right) {
            // swap arr[left] and arr[right]
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        // print reversed array
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5};
        int result[]=reverseArray(arr);
        for(int i=0;i< arr.length;i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
        reverse();

    }
}
