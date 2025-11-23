package Test;

public class NoOfDigitInThatNum {
    public static void main(String[] args) {
        int number=34305;
        int count=0;
        while(number!=0)
        {
            number=number/10;
            count++;
        }
        System.out.println("number of digits "+count);
//        String s=Integer.toString(number);
//        System.out.println(s.length());

    }
}
