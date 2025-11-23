package Collection.PriorityQueue;

import java.util.PriorityQueue;

public class Test1 {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(40);
        pq.add(10);
        pq.add(20);
        pq.add(30);

        System.out.println("Original PriorityQueue: " + pq);

        // Removing elements using poll()
        System.out.println("Removed: " + pq.poll()); // 10
        System.out.println("Removed: " + pq.poll()); // 20

        System.out.println("After removal: " + pq);
    }
}
