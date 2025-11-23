package MultiThreading;

public class PrintNumbersWithThreeThreads implements Runnable {

    private static final int NO_OF_THREADS = 3; // 3 threads
    private static final int MAX_DIGIT = 20; // Print numbers up to 10
    private int current = 1;
    private final Object lock = new Object();

    public static void main(String[] args) {
        PrintNumbersWithThreeThreads printNumbers = new PrintNumbersWithThreeThreads();
        Thread t1 = new Thread(printNumbers, "T1");
        Thread t2 = new Thread(printNumbers, "T2");
        Thread t3 = new Thread(printNumbers, "T3");

        t1.start();
        t2.start();
        t3.start();
    }

    @Override
    public void run() {
        while (true) {    //while (true): This loop keeps running indefinitely until all numbers are printed.
            synchronized (lock) {
             //    Check if all numbers are printed
                if (current > MAX_DIGIT) {
                    lock.notifyAll(); // Ensure all waiting threads exit
                    break;
                }

                // Identify the correct thread that should print
                int expectedThreadId = (current - 1) % NO_OF_THREADS + 1; //When current = 1: (1 - 1) % 3 + 1 = 0 % 3 + 1 = 1, so expectedThreadId = 1.
                String expectedThreadName = "T" + expectedThreadId;       //When current = 2: (2 - 1) % 3 + 1 = 1 % 3 + 1 = 2, so expectedThreadId = 2.
                                                                          //When current = 3: (3 - 1) % 3 + 1 = 2 % 3 + 1 = 3, so expectedThreadId = 3.
                                                                          //And this pattern repeats as current increases.
                if (!Thread.currentThread().getName().equals(expectedThreadName)) {
                    try {
                        lock.wait(); // Wait if it's not this thread's turn
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    continue; // Check again after waking up
                }

                // Print the current number
                System.out.println("Thread " + Thread.currentThread().getName() + ": " + current);

                current++; // Move to the next number

                lock.notifyAll(); // Wake up other threads
            }
        }
    }
}
//The run() method is the entry point for the code executed by each thread.
//while (true): This loop keeps running indefinitely until all numbers are printed.
//synchronized (lock): This block ensures that only one thread can execute the code inside it at a time. The lock object is used to ensure mutual exclusion, meaning only one thread can access the critical section at a time.
//if (current > MAX_DIGIT): This checks if the current number exceeds the MAX_DIGIT (20). If so, it breaks the loop and stops printing. It also calls lock.notifyAll() to wake up any waiting threads to make sure they exit gracefully.
//if (!Thread.currentThread().getName().equals(expectedThreadName)): This checks if the current thread's name matches the expected thread's name. If it doesn't match (i.e., it's not this thread's turn), the thread waits.
//Round 1 (current = 1):
//Thread T1 starts:
//
//Expected Thread for current = 1: The calculation (1 - 1) % 3 + 1 = 1 means T1 is expected to print.
//T1 prints: "Thread T1: 1"
//current is incremented: current = 2
//lock.notifyAll(): Notify other threads to check if it's their turn.
//Thread T2 checks:
//
//Expected Thread for current = 2: The calculation (2 - 1) % 3 + 1 = 2 means T2 is expected to print.
//T2 waits: Since it's not T2's turn, T2 waits using lock.wait().
//Thread T3 checks:
//
//Expected Thread for current = 2: The calculation (2 - 1) % 3 + 1 = 2 means T3 is not expected to print yet, so T3 waits.
//Round 2 (current = 2):
//Thread T2 now prints:
//
//Expected Thread for current = 2: Now it's T2's turn (expectedThreadName = T2).
//T2 prints: "Thread T2: 2"
//current is incremented: current = 3
//lock.notifyAll(): Notify other threads to check if it's their turn.
//Thread T1 checks:
//
//Expected Thread for current = 3: The calculation (3 - 1) % 3 + 1 = 3 means T1 waits because it's not T1's turn.
//Thread T3 checks:
//
//Expected Thread for current = 3: The calculation (3 - 1) % 3 + 1 = 3 means T3 is expected to print.
//T3 prints: "Thread T3: 3"
//current is incremented: current = 4
//lock.notifyAll(): Notify other threads to check if it's their turn.
//Round 3 (current = 4):
//Thread T1 now prints:
//
//Expected Thread for current = 4: The calculation (4 - 1) % 3 + 1 = 1 means T1 is expected to print.
//T1 prints: "Thread T1: 4"
//current is incremented: current = 5
//lock.notifyAll(): Notify other threads to check if it's their turn.
//Thread T2 checks:
//
//Expected Thread for current = 5: The calculation (5 - 1) % 3 + 1 = 2 means T2 is expected to print.
//T2 prints: "Thread T2: 5"
//current is incremented: current = 6
//lock.notifyAll(): Notify other threads to check if it's their turn.
//Round 4 (current = 6):
//Thread T3 now prints:
//Expected Thread for current = 6: The calculation (6 - 1) % 3 + 1 = 3 means T3 is expected to print.
//T3 prints: "Thread T3: 6"
//current is incremented: current = 7
//lock.notifyAll(): Notify other threads to check if it's their turn.