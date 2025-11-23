package Interview;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPrimeNumberInArray {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7,8,9};
        List<Integer>list=new ArrayList<>();
//        for(int i=0;i< arr.length;i++){
//            if(isPrime(arr[i])){
//                list.add(arr[i]);
//            }
//        }
        for(int x:arr){
            if(isPrime(x)){
                list.add(x);
            }
        }
        System.out.println(list);

    }
    static boolean isPrime(int x){
        if(x<=1) return false;
        for(int i=2;i<=Math.sqrt(x);i++){
            if(x%i==0) return false;
        }
        return true;
    }
}
