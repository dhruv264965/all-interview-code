package BasicMath;

public class Palindrome_Number {
    //Example 1:
    //
    //Input: x = 121
    //Output: true
    //Explanation: 121 reads as 121 from left to right and from right to left.
    //Example 2:
    //
    //Input: x = -121
    //Output: false
    //Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
    //Example 3:
    //
    //Input: x = 10
    //Output: false
    //Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

    public  boolean isPalindrome(int x) {
        if(x<0)return false;
        int org=x;
        int temp;
        int sum=0;
        while(x!=0){
            temp=x%10;///121--1 12--2
            sum=(sum*10)+temp;///1  112
            x=x/10;
        }
        return sum==org;

    }
    public static void main(String[] args) {
        Palindrome_Number palindrome_number=new Palindrome_Number();
        System.out.println(palindrome_number.isPalindrome(121));

    }
}
