package Revision;

import java.util.HashMap;

public class longSubStringwithoutrep {
    public static void main(String[] args) {
        String s="abcaa";
        HashMap<Character,Integer>mp=new HashMap<>();
        int i=0,j=0,result=0;
        while (j<s.length()){
            char c=s.charAt(j);
            if(mp.containsKey(c)){ // agar hamara character repeat karta hai then i index ko update kar denge
                i=Math.max(mp.get(c),i);
            }
            result=Math.max(result,j-i+1);
            mp.put(c,j+1);
            j++;

        }
        System.out.println(result);
    }
}
