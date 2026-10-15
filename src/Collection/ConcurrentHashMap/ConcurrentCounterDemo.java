package Collection.ConcurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Shows WHY ConcurrentHashMap is needed: 10 threads each increment the same key 10_000 times.
// Expected final count = 100_000.
public class ConcurrentCounterDemo {

    static final int THREADS = 10;
    static final int INCREMENTS = 10_000;

    // Runs the given task in THREADS threads at the same time and waits until all finish.
    static void runInParallel(Runnable task) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        CountDownLatch startGun = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(THREADS);
        for (int i = 0; i < THREADS; i++) {
            pool.submit(() -> {
                try {
                    startGun.await();           // all threads start together -> more contention
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }
        startGun.countDown();
        done.await();
        pool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        int expected = THREADS * INCREMENTS;

        // 1) HashMap - NOT thread safe -> lost updates (result usually < expected, may even throw)
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("count", 0);
        try {
            runInParallel(() -> {
                for (int i = 0; i < INCREMENTS; i++) {
                    hashMap.put("count", hashMap.get("count") + 1);
                }
            });
        } catch (Exception e) {
            System.out.println("HashMap threw: " + e);
        }
        System.out.println("1) HashMap                     : " + hashMap.get("count") + " (expected " + expected + ")");

        // 2) ConcurrentHashMap with get() then put() - map is safe, but the SEQUENCE is not atomic
        //    -> still lost updates. This is the most common interview trap.
        ConcurrentHashMap<String, Integer> wrong = new ConcurrentHashMap<>();
        wrong.put("count", 0);
        runInParallel(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                wrong.put("count", wrong.get("count") + 1);   // check-then-act, NOT atomic
            }
        });
        System.out.println("2) CHM get() + put()           : " + wrong.get("count") + " (expected " + expected + ")");

        // 3) ConcurrentHashMap with merge() - atomic read-modify-write -> always correct
        ConcurrentHashMap<String, Integer> right = new ConcurrentHashMap<>();
        runInParallel(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                right.merge("count", 1, Integer::sum);        // atomic
            }
        });
        System.out.println("3) CHM merge()                 : " + right.get("count") + " (expected " + expected + ")");

        // 4) Different keys -> different buckets -> threads do not block each other.
        ConcurrentHashMap<Integer, Integer> perThread = new ConcurrentHashMap<>();
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        CountDownLatch done = new CountDownLatch(THREADS);
        for (int t = 0; t < THREADS; t++) {
            final int key = t;
            pool.submit(() -> {
                for (int i = 0; i < INCREMENTS; i++) {
                    perThread.merge(key, 1, Integer::sum);
                }
                done.countDown();
            });
        }
        done.await();
        pool.shutdown();
        System.out.println("4) CHM different keys          : " + perThread + " (each should be " + INCREMENTS + ")");

        // 5) computeIfAbsent - the value is created only ONCE even if many threads ask at the same time.
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        java.util.concurrent.atomic.AtomicInteger created = new java.util.concurrent.atomic.AtomicInteger();
        runInParallel(() -> cache.computeIfAbsent("config", k -> {
            created.incrementAndGet();
            return "loaded-config";
        }));
        System.out.println("5) computeIfAbsent created     : " + created.get() + " time(s) (expected 1)");

        // 6) Weakly consistent iterator - no ConcurrentModificationException while modifying.
        ConcurrentHashMap<Integer, String> iterMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) iterMap.put(i, "v" + i);
        for (Integer k : iterMap.keySet()) {
            iterMap.put(k + 100, "added-while-iterating");     // HashMap would throw CME here
        }
        System.out.println("6) Iterate + modify, no CME    : size=" + iterMap.size());

        // 7) Null is not allowed.
        try {
            right.put("x", null);
        } catch (NullPointerException e) {
            System.out.println("7) null value -> NullPointerException");
        }

        // Summary:
        //  1) -> HashMap loses updates (or breaks)
        //  2) -> CHM alone is not enough for check-then-act
        //  3) -> use merge / compute / putIfAbsent for atomic compound operations
    }
}
