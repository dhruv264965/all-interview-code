package StreamApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {
    // Q- Write a program to group of list of student based on their length and count the number of strings of each group
    public static void main(String[] args) {
        List<String> list = Arrays.asList("dog", "cat", "elephant", "zebra", "rabbit", "fox", "ant");
        HashMap<Integer,Integer>freq=new HashMap<>();
        for(String s:list){
            int length=s.length();
            freq.put(length,freq.getOrDefault(length,0)+1);

        }
        for(Map.Entry<Integer,Integer> count:freq.entrySet()){
            System.out.println("String length"+count.getKey()+" :"+count.getValue());
        }
        // java 8
       Map<Integer,Long> groupByLength= list.stream()
               .collect(Collectors.groupingBy(String::length,Collectors.counting()));//method reference is sort form of lamda expression s->s.length
        System.out.println(groupByLength);
        groupByLength.forEach((a,b)-> System.out.println(a+":"+b));

    }
}
