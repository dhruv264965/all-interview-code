package MultiThreading;

public class PrintOddEvenInTwoDiffferentThreadUsingGlobalCounterAndLamdaExp {

    //Using Lamda extression
    private static int counter = 1; // No need for volatile, synchronization will ensure visibility
    private static final Object lock = new Object(); // Lock object for synchronization

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (counter <= 10) {
                    if (counter % 2 == 0) { // Even number
                        System.out.println("T1 prints even: " + counter);
                        counter++;
                        lock.notify(); // Notify t2 to proceed
                    } else {
                        try {
                            lock.wait(); // Wait for t2 to print odd
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                while (counter <= 10) {
                    if (counter % 2 != 0) { // Odd number
                        System.out.println("T2 prints odd: " + counter);
                        counter++;
                        lock.notify(); // Notify t1 to proceed
                    } else {
                        try {
                            lock.wait(); // Wait for t1 to print even
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();

    }




}
