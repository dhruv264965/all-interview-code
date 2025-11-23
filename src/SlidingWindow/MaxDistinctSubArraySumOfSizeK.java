package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;

public class MaxDistinctSubArraySumOfSizeK {
    static int maxDistinctSubArraySumOfSizeKBruteForece(int arr[],int k,int n) {  //T.C.: O(N^2) S.C.: O(N)
     //   int sum=0;
        int max=0;
        for(int i=0;i<n;i++){
            int sum=0;
            HashSet<Integer> st=new HashSet<>();
            for(int j=i;j<i+k && j<n;j++){
                if(st.add(arr[j])==false){////if we get duplicate element then break;
                    break;
                }
                sum+=arr[j];
            }
            if(st.size()==k){
                max=Math.max(max,sum);
            }
        }
        return max;
    }
    static int maxDistinctSubArraySumOfSizeK(int arr[],int k,int n){  /////T.C.: O(N) S.C.: O(N)
        int max=0;
        int sum=0;
        int start=0;
        HashMap<Integer,Integer>hp =new HashMap<>();
        for(int i=0;i<n;i++){

            sum+=arr[i];
            hp.put(arr[i],hp.getOrDefault(arr[i],0)+1);
            if(i-start+1==k){
                if(hp.size()==k){
                    max=Math.max(max,sum);
                }
                sum-=arr[start];
                if(hp.get(arr[start])<=1){
                    hp.remove(arr[start]);

                }
                else {
                    hp.put(arr[start],hp.getOrDefault(arr[start],0)-1);
                }
                start++;
            }
        }
        return max;
    }
    static int maxDistinctSubArraySumOOfSizeK(int arr[],int k,int n){
        HashMap<Integer,Integer> hp=new HashMap<>();
        int i=0,j=0;
        int sum=0;
        int max=0;
        while (j<n){ //{1,3,1,4,2};

            hp.put(arr[j],hp.getOrDefault(arr[j],0)+1);
            sum+=arr[j];
            if(j-i+1==k){
                if(hp.size()==k) {
                    max = Math.max(max, sum);
                }
                sum-=arr[i];
                hp.put(arr[i],hp.get(arr[i])-1);

                if(hp.get(arr[i])==0) {  ///here we are checking for last element have freq more than 1 then dont remove that element
                    hp.remove(arr[i]); ///jab kisi ki ffreq 0 ho jaye to usko remove kar do ..
                }
                i++;

            }
            j++;

        }
//        while (j < arr.length) {
//            sum += arr[j];
//            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);          // add it to map
//                                                       // add to local sum
//            if (j - i + 1 == k) {                                      // if window length == K
//                if (map.size() == k) {                                 // if size of map is k, that means there are k unique elements
//                    maxSum = Math.max(maxSum, sum);                    // take the max sum
//                }
//                sum -= arr[i];                                         // remove calculation of arr[i]
//                map.put(arr[i], map.get(arr[i])-1);                    // remove arr[i] from map. If arr[i] is duplicate in window, decrease the freq by 1
//                if (map.get(arr[i]) == 0) map.remove(arr[i]);
//                i++;                                                   // shift the window to the right
//            }
//            j++;                                                       // expand the window
//        }
        return max;
    }

    public static void main(String[] args) {
        int arr[]={1,3,1,4,2};
        int n=5;
        int k=3;
      //  System.out.println(maxDistinctSubArraySumOfSizeKBruteForece(arr,k,n));
        //System.out.println(maxDistinctSubArraySumOfSizeK(arr,k,n));
        System.out.println(maxDistinctSubArraySumOOfSizeK(arr,k,n));
    }
}
