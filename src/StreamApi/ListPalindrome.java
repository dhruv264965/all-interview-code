package StreamApi;

import java.util.Arrays;
import java.util.List;

public class ListPalindrome {
    public static boolean isPaindrom(String str){
        boolean flag=false;
        for(int i=0, j=str.length()-1; i<=j;i++,j--){
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        List<String> palindrome= Arrays.asList("madam", "cat", "racecar", "java", "level", "world");
        List<Boolean> booleans = palindrome.stream().map(ListPalindrome::isPaindrom).toList(); // [true, false, true, false, true, false]
        System.out.println(booleans);

        List<String> list = palindrome.stream().filter(ListPalindrome::isPaindrom).toList();
        System.out.println(list);

    }
}
