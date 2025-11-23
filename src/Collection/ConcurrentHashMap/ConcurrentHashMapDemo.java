package Collection.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String>hp=new ConcurrentHashMap<>();
        // Writer Thread 1: writes to key "A"
        Thread writer1=new Thread(()->{
            System.out.println("Writer1: putting A -> Apple");
            hp.put("A","apple");
            System.out.println("Writer1: done");
        });
        // Writer Thread 2: writes to key "A"
        Thread writer2 = new Thread(() -> {
            System.out.println("Writer2: putting B -> Banana");
            hp.put("B", "Banana");
            System.out.println("Writer2: done");
        });

        // Reader Thread: reads key "A"
        Thread reader = new Thread(() -> {
            try {
                Thread.sleep(50); // slight delay to allow writer to start
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Reader: reading A -> " + hp.get("A"));
        });
        // Start all threads
        writer1.start();
        writer2.start();
        reader.start();
        try {
            writer1.join();
            writer2.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Map: " + hp);
        //Threads writer1 and writer2 write to different keys. Since keys "A" and "B" hash to different buckets, they can proceed in parallel without blocking each other.
        //
        //The reader reads key "A" while writes are happening — it still gets consistent and safe results due to internal optimizations.
        //
        //Final output shows all values correctly stored and no data corruption.



    }
}
