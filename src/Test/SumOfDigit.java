package Test;

public class SumOfDigit {
    public static void main(String[] args) {
        int sum=0;
        int num=65647;
        while(num!=0)
        {
            int rem=num%10;
                sum+=rem;
                num=num/10;
        }
        System.out.println(sum);
    }
}
