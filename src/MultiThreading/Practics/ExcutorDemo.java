package MultiThreading.Practics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExcutorDemo {
    public static void main(String[] args) {

        ExecutorService executorService= Executors.newFixedThreadPool(5); //creating thread pools for 5 workers.
        // if we increase the worker time will increase. But increasing thread pool like 100 will not help.we need to take some min value upto 10 for 20 task
        Long startTime=System.currentTimeMillis(); //to check how much time taken tow orker to complete the task
        for(int i=0;i<20;i++) {
            executorService.submit(() -> {
                System.out.println("Task Running in : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10);  // if we are giving 10ms time for worker to complete task // add some processing time for excution.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        executorService.shutdown(); // when shutdown() method is called on an executor service, it stops accepting new tasks, waits for previously submitted tasks to execute, and then terminates the executor.
        //executorService.shutdownNow();//this method interrupts the running task and shuts down the executor immediately.
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES); // if we not use this "Done"will call first.but we want "done" excute at last
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime=System.currentTimeMillis();
        System.out.println("Total time taken "+(endTime-startTime)+"ms"); //if we increase the worker total time will reduce
        System.out.println("done");
    }
}
