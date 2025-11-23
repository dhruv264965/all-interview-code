package BasicJava;

public class CountDigit {
    public static void main(String[] args) {
        int x=5678;
        int count=0;
        while (x!=0){
            x=x/10;
            count++;
        }
        System.out.println(count);
        int temp=5678;
        int sum=0;
        while (temp!=0){
            int rem=temp%10;
            sum=sum+rem;
            temp=temp/10;
        }
        System.out.println(sum);
    }

}
