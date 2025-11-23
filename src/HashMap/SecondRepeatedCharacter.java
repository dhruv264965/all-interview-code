package HashMap;

import java.util.HashSet;

public class SecondRepeatedCharacter {
    public static void main(String[] args) {
        String str = "Javav Stream Api is very good concept";
        HashSet<Character> map = new HashSet<>();
        HashSet<Character> repeated = new HashSet<>();

        for (char ch : str.toCharArray()) {

            if (map.contains(ch) && !repeated.contains(ch)) {

                repeated.add(ch);
                if(repeated.size()==2){
                    System.out.println(ch);
                    return;
                }
            }
            map.add(ch);
        }

        System.out.println("No repeated character found");
    }
}
