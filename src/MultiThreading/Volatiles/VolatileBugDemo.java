package MultiThreading.Volatiles;

class Task1 implements Runnable {
  //  private  boolean running = true; // ❌ not volatile      // Task started...
                                                                //Requesting stop...
    private volatile boolean running = true; // volatile        // Task started...
                                                                 // Requesting stop...
                                                                  //   Task stopped...

    @Override
    public void run() {
        System.out.println("Task started...");
        while (running) {
            // do work
        }
        System.out.println("Task stopped...");
    }

    public void stop() {
        running = false;
    }
}

public class VolatileBugDemo {
    public static void main(String[] args) throws InterruptedException {
        Task1 task = new Task1();
        Thread t1 = new Thread(task);
        t1.start();

        Thread.sleep(1000); // let it run for 1 second
        System.out.println("Requesting stop...");
        task.stop(); // set running = false
    }
}

