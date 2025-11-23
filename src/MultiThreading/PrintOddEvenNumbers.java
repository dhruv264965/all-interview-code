package MultiThreading;



public class PrintOddEvenNumbers implements Runnable {

    private static final int MAX_DIGIT = 10; // Print numbers up to 10
    private static final String THREAD_ODD = "T1";  // Odd thread
    private static final String THREAD_EVEN = "T2"; // Even thread

    private int current = 1;
    private final Object lock = new Object();

    public static void main(String[] args) {
        PrintOddEvenNumbers printNumbers = new PrintOddEvenNumbers();

        Thread t1 = new Thread(printNumbers, THREAD_ODD); // Thread 1 prints odd
        Thread t2 = new Thread(printNumbers, THREAD_EVEN); // Thread 2 prints even

        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                // Stop if all numbers are printed
                if (current > MAX_DIGIT) {
                    lock.notifyAll();
                    break;
                }

                boolean isOdd = current % 2 != 0;
                String expectedThread = isOdd ? THREAD_ODD : THREAD_EVEN;

                // Check if this thread should print
                if (!Thread.currentThread().getName().equals(expectedThread)) {
                    try {
                        lock.wait(); // Wait for the correct thread
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    continue; // Retry after waking up
                }

                // Print the current number in the required format
                if (isOdd) {
                    System.out.println(Thread.currentThread().getName() + " is odd: " + current);
                } else {
                    System.out.println(Thread.currentThread().getName() + " is even: " + current);
                }

                current++; // Move to the next number

                lock.notifyAll(); // Wake up other threads
            }
        }
    }
}

