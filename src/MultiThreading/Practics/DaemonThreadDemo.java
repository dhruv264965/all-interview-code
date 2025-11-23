package MultiThreading.Practics;
//A daemon thread is a thread that runs in the background and does not prevent the Java Virtual Machine (JVM)
// from exiting when all non-daemon threads in Java have been completed. These threads are typically used to
// perform background tasks such as garbage collection, monitoring, and other system-level operations.
public class DaemonThreadDemo {
    public static void main(String[] args) {
        // daemon thread behavior
        Runnable objRunnable=()->{
            System.out.println("Daemon thread started: " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//The "Daemon thread completed" message may not appear because the JVM exits when the main thread finishes execution.
            System.out.println("Daemon thread completed"); // May not execute if JVM exits early
        };
        Thread objThread=new Thread(objRunnable);
        objThread.setDaemon(true); // if we using daemon thread it is not wait it will excute
        objThread.start();
        System.out.println("Main thread started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread ended");

    }
}
