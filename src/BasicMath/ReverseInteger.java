package BasicMath;

public class ReverseInteger {

    //Example 1:
    //
    //Input: x = 123
    //Output: 321
    //Example 2:
    //
    //Input: x = -123
    //Output: -321
    //Example 3:
    //
    //Input: x = 120
    //Output: 21

    public static void reverse(int x) {
        int temp=x;
       // String sum="";  // this is another way to reverse the number
        int reverseNunber=0;
        while (temp!=0){
               int remainder= temp%10;
              // sum+=Integer.toString(a);
            reverseNunber= reverseNunber*10+remainder;
                temp=temp/10;
        }
        System.out.print(reverseNunber);
        int x1 = 123;
        int reversed = 0;

        while (x1 != 0) {
            int digit = x1 % 10;        // get last digit
            reversed = reversed * 10 + digit; // append digit
            x1 = x1 / 10;                // remove last digit
        }

        System.out.println("Reversed number: " + reversed);
    }
    public static void main(String[] args) {
        reverse(123);

    }
}
