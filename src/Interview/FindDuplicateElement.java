package Interview;

import java.util.HashMap;
import java.util.Map;

public class FindDuplicateElement {
    public static void main(String[] args) {
        int arr[]={2,5,7,9,6,4,2,5,7};
        HashMap<Integer,Integer>map=new HashMap<>();
       for(int num:arr){
           map.put(num,map.getOrDefault(num,0)+1);
       }
        System.out.println(map);
       for(Map.Entry<Integer,Integer>entry:map.entrySet()){
           if(entry.getValue()>1){
               System.out.println(entry.getKey());
           }
       }
    }
}
