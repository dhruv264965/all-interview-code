package MultiThreading.Synchronization;
//Synchronization: It is the process of controlling how and when multiple processes or threads access
//             the shared resources ensuring that only one can enter the critical section at same time.
public class Test {
    public static void main(String[] args) throws InterruptedException {
       Counter counter=new Counter();
       MyThread t1=new MyThread(counter)   ;
       MyThread t2=new MyThread(counter)   ;
       t1.start();
       t2.start();
       t1.join();
       t2.join();
        System.out.println(counter.getCount());
    }
}
