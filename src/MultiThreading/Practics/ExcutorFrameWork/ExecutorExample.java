package MultiThreading.Practics.ExcutorFrameWork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool of size 3
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // Submit tasks to the ExecutorService
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskId + " executed by thread: " + Thread.currentThread().getName());
            });
        }

        // Shutdown the ExecutorService after all tasks are submitted
        executorService.shutdown();
    }
}
