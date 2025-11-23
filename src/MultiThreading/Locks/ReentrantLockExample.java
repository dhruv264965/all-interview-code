package MultiThreading.Locks;

import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } finally {
            lock.unlock(); // Always release the lock in a `finally` block
        }
    }
}

class Worker extends Thread {
    private final SharedResource resource;

    public Worker(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.increment();
        }
    }
}

public class ReentrantLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread t1 = new Worker(resource);
        Thread t2 = new Worker(resource);

        t1.start();
        t2.start();
    }
}

