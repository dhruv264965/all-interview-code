package StreamApi;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        // brute force
//        int maxLen=0;
//        String str="abcdabcse";
//        HashSet<Character>charSet=new HashSet<>();
//        for(int i=0;i<str.length();i++){
//            for(int j=i;j<str.length();j++){
//                if(charSet.contains(str.charAt(j))){
//                    break;
//                }
//                charSet.add(str.charAt(j));
//            }
//            maxLen=Math.max(maxLen, charSet.size());
//        }
//        System.out.println(maxLen);


        /// optimal using sliding window
        int maxLen=0;
        String str="pwwkew";
        HashSet<Character>hp=new HashSet<>();
        int left=0;
        for(int right=0;right<str.length();right++){
            char ch=str.charAt(right);
            while (hp.contains(ch)){
                 char leftChar=str.charAt(left);
                 hp.remove(leftChar);
                left++;
            }
            hp.add(ch);
            maxLen=Math.max(maxLen,right-left+1);
        }
        System.out.println(maxLen);
    }


}
