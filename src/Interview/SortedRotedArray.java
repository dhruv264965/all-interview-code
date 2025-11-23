package Interview;

public class SortedRotedArray { // log n time complexcity
    static int rotate(int arr[], int target, int left, int right){
        while (left<=right){
            int mid=left+(right-left)/2;
            if(arr[mid]==target){
               return mid;
            }
            // if left part is sorted
            if(arr[left]<=arr[mid]){
                if(target>=arr[left] && target<arr[mid]){
                    right=mid-1;
                }
                else {
                    left=mid+1;
                }
            }
            else {
                if(target>arr[mid] && target<=arr[right]){
                    left=mid+1;
                }
                else {
                    right=mid-1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[]={7,8,9,0,1,2,3,4,5,6};
        int target =77;
        int left=0;
        int right= arr.length-1;
        System.out.println(rotate(arr,target,left,right));


    }
}
