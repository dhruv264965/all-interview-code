package Interview;

import OOPS.Abstraction.I;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountAllCharacterInList {
    public static void main(String[] args) {
        List<String>list= Arrays.asList("apple","banana","orangep");
        HashMap<Character, Integer>hp= new HashMap<>();
        for(String name: list){
            for(char c: name.toCharArray()){
                hp.put(c,hp.getOrDefault(c,0)+1);
            }
        }
        System.out.println(hp);

        // using stream

        Map<Character, Long> collect = list.stream().flatMap(str -> str.chars().mapToObj(ch -> (char) ch))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
    }
}
