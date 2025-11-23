package STRING;

public class Palindrome {
    public static boolean ispalindrome(String str)
    {String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        boolean flag=true;
        for (int i = 0, j = cleaned.length() - 1; i<=j ; i++, j--) {
            if (cleaned.charAt(i) !=cleaned.charAt(j))
            {
                flag=false;
            }
            }
        return flag;

    }
    public static void ispalindromeOneLoop(String str)
    {
        int n=str.length();
        for (int i=0;i<n/2;i++){//madam
            if(str.charAt(i)!=str.charAt(n-1-i)){
                System.out.println("Not palindrome");
                return;
            }
        }
        System.out.println(" palindrome");
    }

    public static void main(String[] args) {
     //   String str="Was it a car or a cat I saw?";
        String str="0P";
        System.out.println(ispalindrome(str));
       ispalindromeOneLoop(str);
    }

}
