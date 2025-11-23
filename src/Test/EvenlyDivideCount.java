package Test;

import javax.swing.plaf.IconUIResource;

public class EvenlyDivideCount {
    public static void main(String[] args) {
        int num=22074;
        int count=0;
        int temp=num;
        while (temp>0)
        {
            int rem=temp%10;

            if(rem!=0 && num%rem==0  )
            {
                count++;
            }
            temp=temp/10;

        }
        System.out.println(count);
    }
}
