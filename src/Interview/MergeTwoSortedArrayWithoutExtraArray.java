package Interview;

public class MergeTwoSortedArrayWithoutExtraArray {
    public static void main(String[] args) {
        int arr1[]={1,2,3,9,0,0,0};
        int arr2[]={6,16,18};
        int m=4;
        int n=arr2.length;
        int i=m-1;
        int j=n-1;
        int k=m+n-1;
        while (i>=0 && j>=0){
            if(arr1[i]<arr2[j]){
                arr1[k--]=arr2[j--];
            }
            else {
                arr1[k--]=arr1[i--];
            }
        }
        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }
        for (int x: arr1){
            System.out.print(x+" ");
        }
    }
}
