package MultiThreading.ClassAndObjectLevelSynch;
// class level
class Counterr {
    private static int count = 0;

    // Synchronized method at class level
    public synchronized static void increment() {
        count++;
    }

    public synchronized static int getCount() {
        return count;
    }
}

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        // Create two threads to increment the static counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Counterr.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Counterr.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Both threads access the static method, synchronized at the class level
        System.out.println("Final Count: " + Counterr.getCount());  // Should print 2000
    }
}

