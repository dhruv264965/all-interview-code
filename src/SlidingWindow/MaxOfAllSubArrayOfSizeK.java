package SlidingWindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxOfAllSubArrayOfSizeK {
    //Given an array of integers A.  There is a sliding window of size B which is moving from the very left of the array to the very right.
    //You can only see the w numbers in the window. Each time the sliding window moves rightwards by one position. You have to find the maximum for each window.
    // Input 1:
    //    A = [1, 3, -1, -3, 5, 3, 6, 7]
    //    K = 3
    //Output 1:
    //    C = [3, 3, 5, 5, 6, 7]
    static int[] maxOfAllSubArrayBruteForce(int arr[],int k){
        List<Integer>list=new ArrayList<>();
        int n= arr.length;
        for(int i=0;i<=n-k;i++){ // Loop to iterate over all possible starting points of subarrays
                                //n-k because n-k element result mai store honge
            int max=arr[i];
            for(int j=i; j<i+k;j++){
                if(arr[j]>max) {
                    max = arr[j];
                }
            }
            list.add(max);
        }
        // Convert the list to an int array and return it
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
    static int[] maxOfAllSubArrayOptimal(int arr[],int k){
        int n=arr.length;
        int ans[]=new int[n-k+1]; //requried to store element 8-3+1=6 . total 6 element need to store ans(3 3 5 5 6 7)
        Deque<Integer>q=new LinkedList<>(); //in Queue we can retrive from front and last and delete from front and last. Deque is under LinkedList nterface
        int i=0,j=0;
        while (j<n){
            // calculation
            if(q.size()==0){ // adding first element in the queue and intially size is zero { 1,3,-1,-3,5,3,6,7 }
                q.add(arr[j]);   //[1]
            }
            else {
                while (q.size()>0 && q.peekLast()<arr[j]){ // if 1 is already added again we need to add 3.so there is no use of 1 now so remove 1 from queue { 1,3,-1,-3,5,3,6,7 }
                    q.removeLast(); // [1] -->[] removeLast because latest wo add huya hai usko delete karo then new one add karo
                }
                q.add(arr[j]);  // [3]
            }
            // now move j pointer
            if(j-i+1<k){
                j++;
            }
          else   if(j-i+1==k){
                // answer -> calculation;
                ans[i]=q.peekFirst(); //if we hit window { 1,3,-1,-3,5,3,6,7 } -->[3,-1] --> 3 ko ans mai dalo
                // slide the window
                // calculation
                if(arr[i]==q.peekFirst()){ // {1,3,-1}->a[i]=1, peekFirst=3 ->false -> a[i]=3, peekFirst=3- remove 3
                    q.removeFirst();
                }
                // now slide the pointer
                i++;
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
          int arr[]={ 1,3,-1,-3,5,3,6,7 };
          int k=3;
//        int[] result = maxOfAllSubArrayBruteForce(arr, k);
//
//
//        // Print the result array
//        for (int val : result) {
//            System.out.print(val + " ");
//        }
        int[] result=maxOfAllSubArrayOptimal(arr,k);
        for(int val: result){
            System.out.print(val+" ");
        }
        //System.out.println(maxOfAllSubArrayOptimal(arr,k));
    }
}
