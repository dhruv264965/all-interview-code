package Test;

public class NestedSumOfDigit {
    public static void main(String[] args) {

        int start=5019;
        int end=5028;
        for(int i=start;i<=end;i++)
        {
            start=i;
            int sum = 0;
           while(start>0){
                int rem=start%10;
                sum=sum+rem;
                start=start/10;

            }
            System.out.println(sum);
        }
    }
}
