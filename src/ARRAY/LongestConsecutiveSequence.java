package ARRAY;

import java.util.Arrays;
import java.util.HashSet;

//Given an unsorted array of integers
//nums, return the length of the longest consecutive elements sequence.
//Your algorithm should run in
//O(n) time.
//Example:
//Input: nums = [100, 4, 200, 1, 3, 2]
//Output: 4
//Explanation: The longest consecutive sequence is [1, 2, 3, 4].
public class LongestConsecutiveSequence {
    public static int countLongestConsecutiveSequenceBruteForce(int arr[]){
        Arrays.sort(arr);
        int count =1;               // SPACE-O(1) (Constant space usage).
        int maxCount=1;              // TIME- O(n log n) (due to sorting) + O(n) (due to iteration) = O(n log n).
        for(int i=1;i< arr.length;i++){
            if (arr[i]>arr[i-1] && arr[i]==arr[i-1]+1){ // arr[i]==arr[i-1]+1 - ye hame next element find karne mai help
                   //                                    1 ke baad 2 hai tabhi ham count increase karenegej
                count++;
            }
            else {
                count=1;
            }
            maxCount=Math.max(count,maxCount);
        }
        return maxCount;
    }
    public static int countLongestConsecutiveSequence(int arr[]){
        HashSet<Integer>hp=new HashSet<>();
        for(int num:arr){
            hp.add(num);
        }
        System.out.println(hp);
        int maxLength=0;
        for( int num:hp){
            if(!hp.contains(num-1)){  // Check if num is the start of a sequence and this condition check agar privios number contain hai then waha se hum start nh karenge
                // suppose 3 9 1 10 4 20 2 hai if we get checking for 3 and 2 is already in set then we will move to next element
                // because hame list 1 se start karni nh 3 se hai. if we get the element then we count length of consecutive ele present in hashset
                int currentNum=num;
                int currentLength=1;
                while (hp.contains(currentNum+1)){
                    currentNum++;
                    currentLength++;
                }
                maxLength=Math.max(currentLength,maxLength);
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        int arr[]={3, 9, 1 ,10, 4 ,20 ,21,22,23,24,10,2};
        //System.out.println(countLongestConsecutiveSequenceBruteForce(arr));
        System.out.println(countLongestConsecutiveSequence(arr));
    }
}
//| Metric              | Complexity |
//| ------------------- | ---------- |
//| ⏱ Time Complexity   | O(n)       |
//| 💾 Space Complexity | O(n)       |