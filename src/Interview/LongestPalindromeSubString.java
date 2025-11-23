package Interview;

public class LongestPalindromeSubString {
    public static boolean isPalindrome(String str){
        for(int i=0,j=str.length()-1;i<=j;i++,j--){
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }

        }
        return true;
    }
    public static void main(String[] args) {
        int maxLengthOfSubString=0;
        int start=0 ,end=0;
        String str="caeabbaj";
        for(int i=0;i<str.length();i++){
            for (int j=i;j<str.length();j++){
                String subString=str.substring(i,j+1);
                if(isPalindrome(subString)){
                   if((j-i+1)>maxLengthOfSubString){  // j-i+1 size of palindrome
                       maxLengthOfSubString=j-i+1;
                       start=i;
                       end=j;
                   }
                }
            }
        }
        System.out.println(" length of  Longest Palindrome SubString "+maxLengthOfSubString);
        System.out.println(str.substring(start,end+1));
    }
}
