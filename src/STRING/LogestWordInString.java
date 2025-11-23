package STRING;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogestWordInString {
    public static void main(String[] args) {
        String str="java is a popular language";
        Optional<String> max = Arrays.stream(str.split(" "))
                .max(Comparator.comparingInt(String::length));
        System.out.println(max);
/// 2nd approch
        String s[]=str.split(" ");
        String longest="";
        for(String word:s){
            if(word.length()>longest.length()){
                longest=word;
            }
        }
        System.out.println(longest);



    }
}
