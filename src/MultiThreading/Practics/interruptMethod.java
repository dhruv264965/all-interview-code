package MultiThreading.Practics;
//In Java Threads, if any thread is in sleeping or waiting state (i.e. sleep() or wait() is invoked), calling the interrupt() method on
// the thread, breaks out the sleeping or waiting state throwing InterruptedException. If the thread is not in the sleeping or waiting
// state, calling the interrupt() method performs normal behavior and doesn’t interrupt the thread but sets the interrupt flag to true.
//
//interrupt() method: If any thread is in sleeping or waiting for a state then using the interrupt() method, we can interrupt the
// execution of that thread by showing InterruptedException. A thread that is in the sleeping or waiting state can be interrupted with
// the help of the interrupt() method of Thread class.
public class interruptMethod extends Thread {
    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                Thread.sleep(2000);
                System.out.println("Thread is sleeping");
            } catch (InterruptedException e) {
                System.out.println("Exception "+ e);
            }
           // System.out.println("print "+i);
        }
    }

    public static void main(String[] args) {
        interruptMethod t1=new interruptMethod();
        t1.start();
        t1.interrupt();
        System.out.println("main thread excuting");
    }
}
