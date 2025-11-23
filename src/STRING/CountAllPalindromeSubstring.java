package STRING;

public class CountAllPalindromeSubstring {
    private static boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

        }
        return true;
    }
    public static void main(String[] args) {
        int count=0;
        String s= "fdskslf";
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String ch=s.substring(i,j+1);
                if(isPalindrome(ch)){
                    System.out.println(ch);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
