package Interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FindPairOfgivenSum {
    public static void main(String[] args) {
        int arr[]={10, 6, 7, 8, 9, 13, 3, 2, 8, 12, 13, 5, 15, 4, 8};
        int target=20;
        Set<String> uniquePairs = new HashSet<>();
        HashMap<Integer,Integer>hp=new HashMap<>();
        for(int i=0;i< arr.length;i++){
            int sumCom=target-arr[i];
            if(hp.containsKey(sumCom)){
               // System.out.println("("+ sumCom +" "+ arr[i]+ ")");
                uniquePairs.add("("+ sumCom +" "+ arr[i]+ ")");
             //   uniquePairs.add("("+ hp.get(sumCom) +" "+ hp.get(arr[i])+ ")");
            }
            hp.put(arr[i],i );
        }
        System.out.println("----------------------------");
        for(String x:uniquePairs){
            System.out.println(x);
        }

    }
}
