package ARRAY;

public class Secondlargest {
    static int secondLargest(int arr[]){
//        int max1=Integer.MIN_VALUE;
//        int max2=Integer.MIN_VALUE;
//        for(int i=0;i<arr.length;i++){
//            if(arr[i]>max1){
//                max2=max1;
//                max1=arr[i];
//            }
//            else if(arr[i]>max2 && max1!=arr[i]){  //because we are ilterate through max1 (12 35 1 10 34 1) 35 repeat ho jayega
//                max2=arr[i];
//            }
//            if(max1==max2) return -1;
//        }
//        return max2;

        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        for(int x:arr){
            if(x>max1){
                max2=max1;
                max1=x;
            }
            else if(x>max2 && x!=max1 ){
                max2=x;
            }
        }
        return max2;
    }
    public static void main(String[] args) {
        int arr[]={12 ,35, 1 ,10, 34 ,35,1};
        System.out.println(secondLargest(arr));
    }
}
