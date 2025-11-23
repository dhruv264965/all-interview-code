package Test;

public class Sum_of_digit_equaltoK4 {
    public static void main(String[] args) {
        int n=200;
        int k=15;
        for(int i=1;i<=200;i++)
        {
            int sum=0;
            for(int num=i;num>0;)
            {
                int rem=num%10;
                sum=sum+rem;
                num=num/10;
            }
            if(sum==k)
                System.out.println(i);
        }

    }
}
