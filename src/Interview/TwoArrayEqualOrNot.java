package Interview;

public class TwoArrayEqualOrNot {
    public static void main(String[] args) {
        int arr[]={1,2,3};
        int arr1[]={1,2,3,5};
        int n= arr.length;
        int m= arr1.length;
        if(n!=m) {
            System.out.println("array is not equal");
            return;
        }
        int sum=0;
        int sum1=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        for(int j=0;j<m;j++){
            sum1+=arr1[j];
        }
        if(sum==sum1){
            System.out.println("array is equal");
        }
        else {
            System.out.println("array is not equal");
        }
    }
}
