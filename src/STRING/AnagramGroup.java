package STRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnagramGroup {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");
        // approach 1 sorting TC nlog(n)
        HashMap<String,List<String>> anagramGroup=new HashMap<>();
        for(String word:words){
            char charArray[]=word.toCharArray();
            Arrays.sort(charArray);
            System.out.println(charArray);
            String sortedWord=new String(charArray);
            anagramGroup.computeIfAbsent(sortedWord,k->new ArrayList<>()).add(word); //This ensures that if the key (sorted word) is missing, a new list is created.
                //The word is then added to the appropriate group.
        }
        System.out.println(anagramGroup);
        // Print the grouped anagrams
        System.out.println(new ArrayList<>(anagramGroup.values()));


        // appraoch 2 optimal O(n) TC
    }
}
