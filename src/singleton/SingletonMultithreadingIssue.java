package singleton;
class Singletonn {
    private static Singletonn instance;

    private Singletonn() {
        System.out.println("Singleton instance created");
    }
//Both Thread 1 and Thread 2 check if (instance == null) simultaneously.
//Since instance is null for both threads at the same time, they both proceed to create separate instances of the singleton.
//    public static Singletonn getInstance() { // we are getting two different hashcode with same instance
//        if (instance == null) { // Thread 1 checks
//            try {
//                Thread.sleep(100); // Simulating some delay
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            instance = new Singletonn(); // Thread 2 also creates
//        }
//        return instance;
//    }

    // how to fix it - using synchronized keyword
    public static synchronized Singletonn getInstance() { // we are getting two different hascode with same instance
        if (instance == null) { // Thread 1 checks
            try {
                Thread.sleep(100); // Simulating some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singletonn(); // Thread 2 also creates
        }
        return instance;
    }
}
public class SingletonMultithreadingIssue {
    public static void main(String[] args) {
        Runnable task = () -> {
            Singletonn instance = Singletonn.getInstance();
            System.out.println("Instance HashCode: " + instance.hashCode());
        };

        // Simulate two threads trying to access the Singleton
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
