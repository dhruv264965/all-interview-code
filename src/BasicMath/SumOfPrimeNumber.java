package BasicMath;

public class SumOfPrimeNumber {
//    static boolean isPrime(int num){
//        if(num<=1) return false;
//        if(num==2) return true;
//        if(num%2==0) return false;
//        for (int i=3;i<=Math.sqrt(num);i+=2){
//            if(num%i==0) return false;
//        }
//        return true;
//    }
//    static int sumOfPrimeNumber(int arr[]){
//        int sum=0;
//        for (int num:arr){
//            if(isPrime(num)){
//                sum+=num;
//            }
//        }
//        return sum;
//    }
    public static void main(String[] args) {
        int arr[]={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(sumOfPrimeNumber(arr));

    }
    static Boolean isPrime(int num){
        if(num<0) return false;
        if(num==1) return false;
        for(int i=2;i<=Math.sqrt(num);i++)
        {
            if(num%i==0) {
                return false;
            }
        }
        return true;
    }
    static int sumOfPrimeNumber(int arr[]){
        int sum=0;
        for(int x:arr){
            if(isPrime(x)){
                sum+=x;
            }
        }
        return sum;
    }
}
