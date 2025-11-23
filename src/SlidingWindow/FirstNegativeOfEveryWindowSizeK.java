package SlidingWindow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstNegativeOfEveryWindowSizeK {
    public static long[] printFirstNegativeInteger(long A[], int N, int K)
    {
        List<Long>l=new ArrayList<>();
       // Queue<Long> queue = new LinkedList<>();
        long res[]=new long[N-K+1];

        int i = 0, j = 0;
        int index=0;
        while(j < N) {

            if(A[j] < 0)
                l.add(A[j]);

            if((j - i + 1) < K) {
                j++;
            }
            else if((j - i + 1) == K) {
                if(l.isEmpty()) { ///list agar empty hai to
                    res[index++]=0;
                    //	list.add(0);
                }
                else {
                    res[index++]=l.get(0);
                    //	list.add(queue.peek());
                    if(A[i] == l.get(0))
                       l.remove(0);
                }
                i++;
                j++;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        long A[]={12,-1,-7,8,-15,30,16,28};
        int N=8;
        int K=3;
        long[] result=printFirstNegativeInteger(A,N,K);
        for(long num:result){
            System.out.print(num+" ");
        }
      //  System.out.println(result);

    }
}
