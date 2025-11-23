package BasicMath;

public class SumOfPrimeNumberWithoutSmallestPrime {
    static boolean isPrime(int num){
        if(num<=1) return false;
        if(num==2) return true;
        if(num%2==0) return false;
        for (int i=3;i<=Math.sqrt(num);i+=2){
            if(num%i==0) return false;
        }
        return true;
    }
    static int sumOfPrimeNumberWithoutSmallestPrime(int arr[]){
        int sum=0;
        int smallest_prime= Integer.MAX_VALUE;
        int primeSumWithoutSmallestElement=0;
        for (int num:arr){
            if(isPrime(num)){
                sum+=num;
                if(num<smallest_prime){
                    smallest_prime=num;
                }
            }

        }
        return sum-smallest_prime;
    }
    public static void main(String[] args) {
        int arr[]={1, 7, 3, 4, 5, 6, 2, 8, 9, 10};
        System.out.println(sumOfPrimeNumberWithoutSmallestPrime(arr));

    }
}
