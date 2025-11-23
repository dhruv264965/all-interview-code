package MultiThreading.synchronizedMethod_Block;
class CounterBlock{
    private int count=0;
    public void increment(){

        ////

        //  operation

        ////
        synchronized (this){
            count++;
        }
        ///

        //  operation


        ////


    }

    public int getCount() {
        return count;
    }
}
public class SynchronizedBlockDemo {
    public static void main(String[] args) throws InterruptedException {
        CounterBlock test=new CounterBlock();
        Runnable task =()->{
            for(int i=0;i<1000;i++){
                test.increment();
            }
        };
        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        int start= (int) System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        int end= (int) System.currentTimeMillis();

        System.out.println(test.getCount());
        System.out.println("Time Taken: " + (end - start) + " ms");
    }
}
