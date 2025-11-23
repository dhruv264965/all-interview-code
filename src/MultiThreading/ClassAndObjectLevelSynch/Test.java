package MultiThreading.ClassAndObjectLevelSynch;
// object level
class Counter {
    private int count = 0;

    // Synchronized method at object level
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Create two threads to increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Both threads access the same object
        System.out.println("Final Count: " + counter.getCount());  // Should print 2000
    }
}
