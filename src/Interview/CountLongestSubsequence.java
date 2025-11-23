package Interview;

import java.util.HashSet;

public class CountLongestSubsequence {
    public static void main(String[] args) {
        int arr[] = {100,4,200,3,2,1};
        HashSet<Integer>hashSet=new HashSet<>();
        for(int x:arr){
            hashSet.add(x);
        }
        int maxCount =0;
        for(int y:hashSet){
            if(!hashSet.contains(y-1)){
                int currentNum=y;
                int currentCount=0;
                while (hashSet.contains(currentNum)){
                    currentNum++;
                    currentCount++;
                }
                maxCount=Math.max(currentCount,maxCount);

            }
        }
        System.out.println(maxCount);
    }
}

