package STRING;

public class CountPalidromeSubString {
    static boolean isPalindrome(String str) {
       for(int i=0,j=str.length()-1;i<=j;i++,j--){
           if(str.charAt(i)!=str.charAt(j)) return false;
       }
       return true;
    }

    static void subString(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) { // corrected loop condition
                System.out.print(str.substring(i, j) + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("Total substrings: " + count);
    }

    public static void main(String[] args) {
        String str = "abc";
        if (isPalindrome(str)) {
            System.out.println("Palindrome: " + str);
            subString(str);
        } else {
            System.out.println("Not a palindrome.");
        }
    }
}
