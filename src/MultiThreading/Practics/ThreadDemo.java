package MultiThreading.Practics;

public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println("hello3 --"+Thread.currentThread().getName());
        MyThread myThread=new MyThread();
      //  myThread.run();
        myThread.setName("Thread 0 ");//main is also thread and this is also thread so both thread excute
        myThread.start(); // thread will start if we not write join it will not wait .it will print "done" message

        MyThread myThread1=new MyThread();
        myThread1.setName("Thread 1 ");
        myThread1.start();
        try {
            myThread.join();
            myThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done");

    }
}
