# ReccentrantLock

```text
ReentrantLock is a class in the java.util.concurrent.locks package that gives you explicit control over locking
 — unlike synchronized which is implicit.

It is called "Reentrant" because the same thread can acquire the lock multiple times without causing a deadlock
(just like synchronized).
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            counter++;
        } finally {
            lock.unlock(); // Always release the lock
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        MyReentrantLockExample example = new MyReentrantLockExample();
        example.increment();
        System.out.println("Counter: " + example.getCounter());
    }
}
| Feature                     | Description                                                         |
| --------------------------- | ------------------------------------------------------------------- |
| 🔁 **Reentrant**            | Same thread can lock multiple times                                 |
| 🔒 **Explicit lock/unlock** | You manually lock and unlock (try-finally)                          |
| ⏱️ **tryLock()**            | Try acquiring the lock without blocking                             |
| ⌛ **tryLock(timeout)**      | Try acquiring lock with timeout                                     |
| 🛑 **lockInterruptibly()**  | Locking can be interrupted (unlike synchronized)                    |
| ⚖️ **Fairness**             | Lock acquisition order can be fair (FIFO)                           |
| 🔔 **Condition variables**  | Advanced wait/notify mechanism (like `wait()/notify()`, but better) |

```
