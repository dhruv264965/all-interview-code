package MultiThreading;

public class StopOneThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Running: " + name);
                try {
                    Thread.sleep(10000); // Simulate work
                } catch (InterruptedException e) {
                    System.out.println("Interrupted during sleep: " + name);
                    break; // Exit loop when interrupted
                }
            }
            System.out.println("Stopped: " + name);
        };

        Thread[] threads = new Thread[5];

        // Start 5 threads
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(task, "Thread-" + i);
            threads[i].start();
        }

        Thread.sleep(10000);         // Let all run for 3 sec
        threads[2].interrupt();     // ✅ Stop only Thread-2
    }
}
