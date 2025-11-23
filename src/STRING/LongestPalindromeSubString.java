package STRING;

public class LongestPalindromeSubString {  // Overall: O(n³) — inefficient for longer strings.
    static boolean isPalindrome(String s){
        for(int i=0,j=s.length()-1;i<=j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int maxLengthOfSubString=0;
        int start=0 ,end=0;
        String s="cabbaq";
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String sub = s.substring(i, j + 1);
                if(isPalindrome(sub)){
                   if((j-i+1)>maxLengthOfSubString){ // j-i+1 size of palindrome
                       maxLengthOfSubString=j-i+1;
                       start=i;
                       end=j;
                   }

                }
            }
        }
        System.out.println(" length of  Longest Palindrome SubString "+maxLengthOfSubString);
        System.out.println(s.substring(start,end+1));
    }
}
