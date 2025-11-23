package MultiThreading.Practics;
//The yield() method of thread class causes the currently executing thread object to temporarily pause and allow other
// threads to execute.
public class yieldMethod extends Thread{
    @Override
    public void run() {
        for(int i=1;i<100;i++){
            System.out.println("runnning "+Thread.currentThread().getName());
           // Thread.yield();
        }
    }

    public static void main(String[] args) {
        yieldMethod t1=new yieldMethod();
        yieldMethod t2=new yieldMethod();
        t1.start();
        t2.start();

   // without yield runnning Thread-0
        //runnning Thread-0
        //runnning Thread-0
        //runnning Thread-0
        //runnning Thread-1
        //runnning Thread-1
        //runnning Thread-1
        //runnning Thread-1

    //with yield
    }
}
