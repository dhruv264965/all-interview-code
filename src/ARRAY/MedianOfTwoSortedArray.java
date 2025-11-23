package ARRAY;

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        int arr[]={1,2};
        int arr1[]={3,4};
        int m= arr.length;
        int n= arr.length;
        int merge[]= new int[n+m];
        int i=0,j=0, k=0;
        while (i<m && i<n){
            if(arr[i]<arr1[j]){
                merge[k++]=arr[i++];
            }
            else {
                merge[k++]=arr1[j++];
            }
        }
        while (i<m){
            merge[k++]=arr[i++];
        }
        while (j<n){
            merge[k++]=arr1[j++];
        }
        int total=m+n;
        if(total%2==1){
            System.out.println( merge[total/2]);
        }
        else {
            System.out.println((merge[(total/2)-1]+merge[total/2])/2.0);
        }

    }
}
