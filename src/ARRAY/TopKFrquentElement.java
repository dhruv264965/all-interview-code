package ARRAY;

import java.util.*;

public class TopKFrquentElement {
    public static  int[] topKFrequent(int[] nums, int k) {
        // aproach- sorting nlog(n) TC
        HashMap<Integer,Integer> hp=new HashMap<>();
        for(int x:nums){
            hp.put(x,hp.getOrDefault(x,0)+1);
        }
        List<Map.Entry<Integer,Integer>>sorted=new ArrayList<>(hp.entrySet());
        sorted.sort((a,b)->b.getValue().compareTo(a.getValue()));

        int result[]=new int[k];
        for(int i=0;i<k;i++){
            result[i]=sorted.get(i).getKey();
        }
        return result;
    }
    public static  int[] topKFrequentOptimal(int[] nums, int k) {
        // approach- priority Queue nlog(k) TC if k is less then less complexity compare to sorting
        HashMap<Integer,Integer> hp=new HashMap<>();
        for(int x:nums){
            hp.put(x,hp.getOrDefault(x,0)+1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>>minHeap=new PriorityQueue<>((a,b)->a.getValue().compareTo(b.getValue()));
        // Step 3: Maintain heap of size k
        for (Map.Entry<Integer, Integer> entry : hp.entrySet()) {
            minHeap.offer(entry); // Add current entry

            if (minHeap.size() > k) {
                minHeap.poll(); // Remove least frequent
            }
        }

        // Step 4: Extract results
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll().getKey();
        }

        return result;

    }
    public static void main(String[] args) {
        int nums[]={1,2,2,3,3,3};
        int k=2;
        int res[]=topKFrequent(nums,k);
        for(int x:res){
            System.out.print(x+" ");
        }
        System.out.println("");
        int res1[]=topKFrequentOptimal(nums,k);
        for(int x:res1){
            System.out.print(x+" ");
        }
//        HashMap<Integer,Integer>hp=new HashMap<>();
//        for(int x:arr){
//            hp.put(x,hp.getOrDefault(x,0)+1);
//        }
//        System.out.println(hp);
//        List<Map.Entry<Integer,Integer>>sorted=new ArrayList<>(hp.entrySet());
//        sorted.sort((a,b)->b.getValue().compareTo(a.getValue()));
//        System.out.println(sorted);
//        List<Integer>result=new ArrayList<>();
//        for(int i=0;i<k;i++){
//            result.add(sorted.get(i).getKey());
//        }
//        System.out.println(result);

    }
}
