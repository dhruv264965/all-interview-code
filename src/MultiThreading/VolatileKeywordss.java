package MultiThreading;
//The volatile keyword in Java is used to ensure that the value of a variable is always read from and written
// to the main memory,
// rather than being cached locally by threads.
//volatile keyword: The volatile keyword ensures that the counter variable is directly read from and written to
// the main memory.
// This means that when one thread modifies the value of counter, other threads immediately see the updated value,
// avoiding cache coherence issues that can arise when threads work with local copies of a variable.
//
//Thread t1:
//
//This thread reads the counter value into its local variable local_counter.
//It then enters a while loop where it checks if local_counter differs from counter. If they are different, it
// prints the updated value of counter and updates its local variable to the new value of counter.
//This loop runs as long as local_counter is less than 10.
//Thread t2:
//
//This thread also reads the counter value into a local variable local_counter1.
//Inside a while loop, it increments the local_counter1, updates the counter, and prints the value.
//It also pauses for 500 milliseconds between iterations using Thread.sleep(500).
//Expected Output:
//t2 will increment the value of counter from 0 to 10, printing "T2 is changed" each time the counter is
// incremented.
//t1 will monitor changes in the counter. Whenever counter is updated by t2, t1 will print "T1 is changed"
// along with the new value.
public class VolatileKeywordss {
    private static volatile int  counter=0;
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            int local_counter=counter; ///store value in cache memory
            while (local_counter<10){
                if(local_counter!=counter){
                    System.out.println("T1 is changed "+counter);
                    local_counter=counter;
                }
            }
        });
        Thread t2=new Thread(()->{

        int local_counter1=counter;
        while (local_counter1<10){
            System.out.println("T2 is changes "+(local_counter1+1));
            counter=++local_counter1;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        });
        t1.start();
        t2.start();
    }
}
//The volatile keyword cannot be used with methods or classes, it only applies to variables.
//In this example, each player thread increments the counter by 1 and prints its updated value. However,
// we need to use the volatile keyword to avoid inconsistencies in the counter.
//
//Without volatile, the counter variable may be cached in the CPU registers, and each thread may have its
// own local copy. As a result, one player thread may update the counter, but other threads may not immediately
// see the updated value due to caching.