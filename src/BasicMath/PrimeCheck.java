package BasicMath;

public class PrimeCheck {

    public static Boolean isPrime(int num) {
        boolean flag=true;
        if(num<=1) return false;
        for(int i=2;i<Math.sqrt(num);i++)
        {
            if(num%i==0)
                return false;
        }

        return true;

    }
    public static void main(String[] args) {
        System.out.println(isPrime(151));

    }
}
