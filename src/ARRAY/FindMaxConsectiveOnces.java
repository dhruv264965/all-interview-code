package ARRAY;

public class FindMaxConsectiveOnces {
    static int maxConsecutiveOnes(int []arr) {
       int MaxConsecutiveOne=0, currentConsecutiveOne=0;
       for(int x:arr){
           if(x==1) {
               currentConsecutiveOne++;
           }
           else {
               currentConsecutiveOne=0;
           }
           MaxConsecutiveOne=Math.max(MaxConsecutiveOne,currentConsecutiveOne);
       }
       return MaxConsecutiveOne;
    }
    public static void main(String[] args) {
        int[] arr={0,1,1,0,1,1,1,2,2,2,2,2,2};

        System.out.println(maxConsecutiveOnes(arr));

    }
}
