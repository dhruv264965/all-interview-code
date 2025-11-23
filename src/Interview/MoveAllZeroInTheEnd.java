package Interview;

public class MoveAllZeroInTheEnd {
    public static void main(String[] args) {
        int arr[]={2,4,6,8,0,7,5,0,6,4,3,0,9};
        int k=0;
        int result[]=new int[arr.length];
        for(int i=0;i< arr.length;i++){
            if(arr[i]!=0){
                result[k++]=arr[i];
            }
        }
        for(int x:result){
            System.out.print(x+" ");
        }

    }
}
