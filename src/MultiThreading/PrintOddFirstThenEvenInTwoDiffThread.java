package MultiThreading;
class Thread3 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i < 10; i += 2) { // Loop through odd numbers
            System.out.println("T1 is odd: " + i);
        }
    }
}

class Thread4 extends Thread {
    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) { // Loop through even numbers
            System.out.println("T2 is even: " + i);
        }
    }
}
public class PrintOddFirstThenEvenInTwoDiffThread {
    public static void main(String[] args) {
        Thread3 t1 = new Thread3(); // Odd numbers
        Thread4 t2 = new Thread4(); // Even numbers

        // Start Thread1 to print odd numbers
        t1.start();
        try {
            // Wait for Thread1 to finish before starting Thread2
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start Thread2 to print even numbers
        t2.start();

    }
}
