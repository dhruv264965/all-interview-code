package Collection.HashMap;

/******************************************************************************
 Can palindrome be formed?
 Given a string, check if palindrome can be formed by rearranging its characters.

 Ex1. string: bangalore ans : false

 Ex2. string: abdybayd ans : true because string can be rearranged as abdyydba

 Expected time complexity - O(n)
 Space complexity - O(n)

 *******************************************************************************/
import java.util.*;
public class Mainn
{
    public static void main(String[] args) {
        String str = "adda";
        //Expected answer is : true

        boolean isPalindromePossible = checkPalindromePossibility(str);
        System.out.println("Is palindrome possible ? : " + isPalindromePossible);
    }

    static boolean checkPalindromePossibility(String str){
        if(str.isEmpty()) return false;
        HashMap<Character,Integer>hp=new HashMap<>();
        for(char ch:str.toCharArray()){
            hp.put(ch,hp.getOrDefault(ch,0)+1);
        }
        int odd=0;
        for(int x:hp.values()){
            if(x%2==1){
                odd++;
            }
        }
        return odd<=1;
    }
}

