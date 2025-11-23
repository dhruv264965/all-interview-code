package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagam {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Approach 1 TC m*nlogn
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String sorted = new String(c);
            if (!hashMap.containsKey(sorted)) {
                hashMap.put(sorted, new ArrayList<>());
            }
            hashMap.get(sorted).add(s);
        }
        return new ArrayList<>(hashMap.values());
    }
















//        HashMap<String,List<String>> hp=new HashMap<>();
//        for(String str:strs){
//            char[] ch=str.toCharArray();
//            Arrays.sort(ch);
//            String sorted=new String(ch);
//            if(!hp.containsKey(sorted)){
//                hp.put(sorted,new ArrayList<>());
//            }
//            hp.get(sorted).add(str);
//        }
//        return new ArrayList<>(hp.values());
//        }
    public static List<List<String>> groupAnagramsOptimal(String[] strs) {
        // Approach 2 TC O(n)
        HashMap<String,List<String>> hp=new HashMap<>();
        for(String str:strs){
            int freq[]=new int[28];   // {"eat", "tea", "tan", "ate", "nat", "bat"};
            for(char ch:str.toCharArray()){
                freq[ch-'a']++;
            }

            StringBuilder sb= new StringBuilder();
            for (int i:freq){
                sb.append("H");
                sb.append(i);
            }
            String converted=sb.toString();
//            if(!hp.containsKey(converted)){         // these 4 line we can write using computeIfAbsent method
//                hp.put(converted,new ArrayList<>());
//            }
//            hp.get(converted).add(str);
            hp.computeIfAbsent(converted,k->new ArrayList<>()).add(str);

        }
        return new ArrayList<>(hp.values());
    }
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
//        System.out.println(groupAnagramsOptimal(strs));
    }
}
