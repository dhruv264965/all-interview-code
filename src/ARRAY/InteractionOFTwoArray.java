package ARRAY;

import java.util.HashSet;

public class InteractionOFTwoArray {
    public static void main(String[] args) {
        int nums1[]={4,9,5};
        int nums2[]={9,4,9,8,4};
        HashSet<Integer>hp=new HashSet<>();
        for (int x:nums1){
            hp.add(x);
        }
        HashSet<Integer>result=new HashSet<>();
        for (int y:nums2){
            if(hp.contains(y)){
                result.add(y);
            }
        }
        System.out.println(result);
    }
}
