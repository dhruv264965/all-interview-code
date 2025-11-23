package Recursion;

public class SumOfNNatural {
    static void sumFirstN(long n){
        if(n<=0) return ;
        long sum=0;
        while (n!=0){
            sum+=n;
            sumFirstN(n-1);
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
//        System.out.println(sumFirstN(3));
    }
}
