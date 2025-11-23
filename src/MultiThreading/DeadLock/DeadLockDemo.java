package MultiThreading.DeadLock;

public class DeadLockDemo {
    public final static Object lockA=new Object();
    public final static Object lockB=new Object();
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            synchronized (lockA){
                System.out.println("Thread 1: Holding lockA");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.println("Thread 1: Holding lockB");
                }
            }
        });

        Thread t2=new Thread(()->{
            synchronized (lockB){
                System.out.println("Thread 2: Holding lockB");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){
                    System.out.println("Thread 2: Holding lockA");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
//output - Thread 1: Holding lockA
//Thread 2: Holding lockB                it got hand due to deadLock


// HOW TO RESOLVE --- USE Reetrant lock

// You have two objects:
//obj1
//obj2

//And two threads:

//t1 → locks obj1 first, then tries to lock obj2
//t2 → locks obj2 first, then tries to lock obj1

//Thread t1 flow
//synchronized (obj1) → acquires lock on obj1.
//Prints "Thead1 obj1".
//Thread.sleep(2000) → t1 holds obj1 lock during sleep (lock is not released during sleep).
//After 2 seconds → tries to synchronized (obj2) → waits until it can get obj2.

//Thread t2 flow

//synchronized (obj2) → acquires lock on obj2.
//Prints "Thead2 obj2".
//Thread.sleep(2000) → t2 holds obj2 lock during sleep.
//After 2 seconds → tries to synchronized (obj1) → waits until it can get obj1.

//Deadlock happens

//After both threads wake up from sleep:
//t1 already holds obj1 and wants obj2, but obj2 is locked by t2.
//t2 already holds obj2 and wants obj1, but obj1 is locked by t1.
//Neither can proceed — both are stuck forever.