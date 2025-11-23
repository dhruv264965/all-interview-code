package MultiThreading.synchronizedMethod_Block;
class CounterMethod{
    private int counter=0;
    public synchronized void increment(){
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
public class SynchronizedMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        CounterMethod test=   new CounterMethod();
        Runnable task =()->{
            for (int i=0;i<10000;i++){
                test.increment();
            }
        };
        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();

        System.out.println(test.getCounter());
        System.out.println("Time Taken: " + (end - start) + " ms");

    }
}
