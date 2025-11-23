package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SequentialPrinting {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        AtomicInteger counter = new AtomicInteger(1);

        // Runnable for printing numbers 1, 2, 3 sequentially
        Runnable task = () -> {
            while (true) {
                synchronized (counter) {
                    // Check if we've printed up to 20
                    if (counter.get() > 20) {
                        break;
                    }
                    // Print numbers 1, 2, 3 sequentially
                    for (int i = 0; i < 3; i++) {
                        if (counter.get() <= 20) {
                            System.out.println(Thread.currentThread().getName() + ": " + counter.getAndIncrement());
                        }
                    }
                }
            }
        };

        // Submit tasks to executor
        executor.submit(task);
        executor.submit(task);
        executor.submit(task);

        // Shutdown executor
        executor.shutdown();
    }
}
