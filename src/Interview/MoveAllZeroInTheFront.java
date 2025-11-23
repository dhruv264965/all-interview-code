package Interview;

public class MoveAllZeroInTheFront {
    public static void main(String[] args) {
        int arr[]={2,4,6,8,0,7,5,0,6,4,3,0,9};
        int k=0;
        int index=arr.length-1;
        int result[]=new int[arr.length];
        for(int i= arr.length-1;i>=0 ;i--){
            if(arr[i]!=0){
                result[index--]=arr[i];
            }
        }
        for(int x:result){
            System.out.println(x);
        }
    }
}
