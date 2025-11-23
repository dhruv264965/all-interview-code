package MultiThreading.Practics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolDemo { //if load is dyamnic then we will use our custom thread pool
    public static void main(String[] args) {
        int corePoolSize  = 5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000; // if my thread is free for this it will go the core Thread pool size
        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize, //intital size of pool
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()
                );
        threadPoolExecutor.submit(()->{
            System.out.println("doing Task1 :"+Thread.currentThread().getName());
        });
        threadPoolExecutor.submit(()->{
            System.out.println("doing Task2  :"+Thread.currentThread().getName());
        });
        threadPoolExecutor.submit(()->{
            System.out.println("doing Task 3 :"+Thread.currentThread().getName());
        });
        threadPoolExecutor.submit(()->{
            System.out.println("doing Task  4:"+Thread.currentThread().getName());
        });
        System.out.println("done");

    }
}
