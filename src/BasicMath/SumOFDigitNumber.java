package BasicMath;

public class SumOFDigitNumber {
    public static void main(String[] args) {
        int a=5432;
        int sum=0;
        while (a!=0){
            int b=a%10;
            sum+=b;
            a=a/10;
        }
        System.out.println(" sum of digit is "+sum);
    }
}
