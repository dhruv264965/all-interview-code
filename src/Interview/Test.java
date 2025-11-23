package Interview;

public class Test {
    public static void main(String[] args) {
      int[]  nums1 = {1,2,3,0,0,0};
      int m=3;
       int[] nums2 = {2,5,6};
       int n=3;
        int i=0,j=0, k=0;
        int result[]=new int[m+n];
        while(i<m && j<n){
            if(nums1[i]<nums2[j]){
                result[k++]=nums1[i++];
            }
            else{
                result[k++]=nums2[j++];
            }
        }
        while(i<m){
            result[k++]=nums1[i++];
        }
        while(j<n){
            result[k++]=nums2[j++];
        }
        for(int x:result){
            System.out.println(x);
        }
    }
}
