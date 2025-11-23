package MultiThreading.Practics;

public class MyTask implements Runnable{
    @Override
    public void run() {
        System.out.println("working on runnable "+Thread.currentThread().getName());
    }
}
