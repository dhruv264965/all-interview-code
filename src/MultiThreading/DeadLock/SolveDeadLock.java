package MultiThreading.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SolveDeadLock {
    static ReentrantLock lock1=new ReentrantLock();
    static ReentrantLock lock2=new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread 1: Acquired lock1");
                    try {
                        if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 1: Acquired lock2");
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.println("Thread 1: Could not acquire lock2, releasing lock1");
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            } catch (InterruptedException ignored) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread 2: Acquired lock2");
                    try {
                        if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 2: Acquired lock1");
                            } finally {
                                lock1.unlock();
                            }
                        } else {
                            System.out.println("Thread 2: Could not acquire lock1, releasing lock2");
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            } catch (InterruptedException ignored) {}
        });

        t1.start();
        t2.start();

    }
}
