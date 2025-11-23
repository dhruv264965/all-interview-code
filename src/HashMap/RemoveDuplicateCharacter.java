package HashMap;

import java.util.LinkedHashSet;

public class RemoveDuplicateCharacter {
    public static void main(String[] args) {
        String str="program";
        LinkedHashSet<Character>hashSet=new LinkedHashSet<>();
        for(int i=0;i<str.length();i++){
           hashSet.add(str.charAt(i));
        }
        System.out.println(hashSet);
        StringBuilder result=new StringBuilder();
        for(char ch:hashSet){
            result.append(ch);
        }
        System.out.println(result.toString());
    }
}
