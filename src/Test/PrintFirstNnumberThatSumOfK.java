package Test;

public class PrintFirstNnumberThatSumOfK {
    public static void main(String[] args) {
        int count=0;
        int targetcount=10;
        int targetsum=6;
        int n=1000;
        for(int i=1;i<n;i++)
        {
            int sum=0;
            for(int num=i;num>0;)
            {
                int rem=num%10;
                sum=sum+rem;
                num=num/10;
            }
            if(sum==targetsum)
            {
                System.out.println(i+"    "+sum);
                count++;
            }
            if(count==targetcount)
            {
                System.out.println(targetcount);
                break;
            }
        }

    }
}
