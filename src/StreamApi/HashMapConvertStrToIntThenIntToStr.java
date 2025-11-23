package StreamApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapConvertStrToIntThenIntToStr {
    public static void main(String[] args) {
        HashMap<String,Integer>hp=new HashMap<>();
        hp.put("Dhruv",32);
        hp.put("raj",22);
        hp.put("amit",18);
        hp.put("anit",32);
        System.out.println(hp);
// If Only One-to-One Mapping (No Duplicate Values)
//If the original map is guaranteed to have unique values, you can reverse like this:
//     Map<Integer, String> reversed = hp.entrySet()
//    .stream()
//    .collect(Collectors.toMap(
//        Map.Entry::getValue,
//        Map.Entry::getKey
//    ));
//        System.out.println(reversed);

        // if duplicate is there
        Map<Integer, List<String>> collect = hp.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));
        System.out.println(collect);


    }
}
