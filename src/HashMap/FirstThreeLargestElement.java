package HashMap;

import java.util.*;
import java.util.stream.Collectors;

public class FirstThreeLargestElement {
    public static void main(String[] args) {
        int arr[]={19,5,78,1,33,11,20};
        HashMap<Integer,Integer>hp=new HashMap<>();
        for(int x:arr){

            hp.put(x, hp.getOrDefault(x,0)+1);
        }
        System.out.println(hp);
        List<Map.Entry<Integer,Integer>>sortedList=new ArrayList<>(hp.entrySet());
        sortedList.sort((a,b)-> b.getKey()-a.getKey());
        System.out.println(sortedList);
        List<Map.Entry<Integer, Integer>> firstThreeLargest = sortedList.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(firstThreeLargest);

        List<Integer> printOnlyKey = firstThreeLargest.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(printOnlyKey);

    }
}
