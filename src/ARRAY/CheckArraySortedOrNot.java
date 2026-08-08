package ARRAY;

public class CheckArraySortedOrNot {
    public static void main(String[] args) {
        int arr[]={7,5,7,9,2};
        boolean flag=true;
        for(int i=1;i< arr.length;i++){
            if(arr[i]<arr[i-1]){
                flag=false;
                break;
            }
        }
        System.out.println(flag);
    }
}
