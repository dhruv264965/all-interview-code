package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class thirdMostFreqElemInArray {
    public static void main(String[] args) {
        int arr[]={1,4,5,7,4,9,4,4,7,9,9};
        HashMap<Integer,Integer>freq=new HashMap<>();
        //count frequency
        for(int num:arr){
            freq.put(num,freq.getOrDefault(num,0)+1);
        }
        System.out.println(freq);
        //sort the freq
        //method-1
        List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(freq.entrySet());
        sortedEntries.sort((a,b)->b.getValue().compareTo(a.getValue()));
//        sortedEntries.sort((a,b)->b.getValue()- a.getValue());
        System.out.println(sortedEntries);
      //method-2
//        List<Map.Entry<Integer,Integer>> sort=freq.entrySet().stream().sorted((e1, e2)-> e2.getValue().compareTo(e1.getValue())).collect(Collectors.toList());
//        System.out.println(sort);

        // 3rd largest freq element
        if(!sortedEntries.isEmpty()){
            System.out.println(sortedEntries.get(2).getKey());
        }
        else {
            System.out.println("Not enough distinct elements for a 3rd most frequent element.");
        }



    }
}
