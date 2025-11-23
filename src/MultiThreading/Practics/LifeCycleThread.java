package MultiThreading.Practics;

public class LifeCycleThread extends Thread{
    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LifeCycleThread t1=new LifeCycleThread();
        System.out.println(t1.getState());  // NEW
        t1.start();
        System.out.println(t1.getState());
        Thread.sleep(1000);
        System.out.println(t1.getState());
        t1.join();  //control will go when t1 completely excute
        System.out.println(t1.getState());



    }
}
