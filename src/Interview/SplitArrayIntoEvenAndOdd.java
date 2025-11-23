package Interview;

public class SplitArrayIntoEvenAndOdd {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7,8,9};
        int res[]=new int[arr.length];
        int k=0;
        for(int x:arr){
            if(x%2==0){
                res[k++]=x;
            }
        }
        for(int x:arr){
            if(x%2!=0){
                res[k++]=x;
            }
        }

        for(int y:res){
            System.out.println(y);
        }
    }
}
