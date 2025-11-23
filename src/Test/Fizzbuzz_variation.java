package Test;

public class Fizzbuzz_variation {
    public static void main(String[] args) {
        int n=16;
        int sum=0;
        for(int i=1;i<=n;i++)
        {
            if(i%3==0 && i%5==0)
            {
                System.out.println("fizzbuzz");
            }
            else if(i%3==0)
            {
                System.out.println("fizz");
            }
            else if(i%5==0)
            {
                System.out.println("buzz");
            }
            else
            {
                System.out.println(i);
                sum+=i;
                System.out.println("sum is"+sum);
            }
            if(sum>3*n)
                break;

        }
    }
}
