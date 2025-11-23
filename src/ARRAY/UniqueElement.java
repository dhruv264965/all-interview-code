package ARRAY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// find a unique number in a given array where all element are being repeated twice with one value being unique
// int arr[]= {1,3,2,4,3,1,2}
//output=4
public class UniqueElement {
    static int[] findUnique(int arr[]){
        for (int i=0;i< arr.length;i++){
            for(int j=i+1;j< arr.length;j++){
                if(arr[i]==arr[j]){
                    arr[i]=-1;
                    arr[j]=-1;
                }
            }
        }
        ArrayList<Integer>uniqueList=new ArrayList<>();
        for(int i =0;i< arr.length;i++){
            if(arr[i]!=-1){
                uniqueList.add(arr[i]);
            }
        }
        int ans[]=new int[uniqueList.size()];
        for(int i=0;i< uniqueList.size();i++){
            ans[i]=uniqueList.get(i);
        }
        return ans;

    }
    static void uniqueElement(int arr[]){
        HashMap<Integer,Integer>freq=new HashMap<>();
        for(int x:arr){
                freq.put(x, freq.getOrDefault(x,0)+1);
        }
        System.out.println(freq);
        for(Map.Entry<Integer,Integer>map:freq.entrySet()){
            if(map.getValue()==1 ){ //if(map.getValue()==1 || map.getValue()==2) if we want element whose freqeuncy is 1 and 2
                System.out.print(map.getKey()+" ");
            }
        }
    }
    public static void main(String[] args) {
        int arr[]= {1,3,2,4,3,1,2,5,6,7,7,1,1};
//        int res[]=findUnique(arr);
//        for(int x:res)
//        System.out.print(x+" ");
          uniqueElement(arr);
    }
}
