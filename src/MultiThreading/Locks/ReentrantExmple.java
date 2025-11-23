package MultiThreading.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//The ReentrantLock is part of the java.util.concurrent.locks package and provides more flexibility and features compared to the
// synchronized keyword. It allows explicit locking and unlocking and provides advanced capabilities like fairness policies,
// interruptible locks, and the ability to try locking without blocking indefinitely.
public class ReentrantExmple {
    private final Lock lock=new ReentrantLock();
    public void outerLoop(){
        lock.lock(); //acquire lock
        try {
            System.out.println("OuterLock");
            innerLoop();
        }
        finally {
            lock.unlock();
        }
    }
    public void innerLoop(){
        lock.lock();
        try {
            System.out.println("InnerLock");
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExmple t1=new ReentrantExmple();
        t1.outerLoop();

    }
}
