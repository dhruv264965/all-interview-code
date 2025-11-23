package Collection.PriorityQueue;

import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<String>();
        System.out.println("add Operation in Queue: " + queue.add("kumar"));
        System.out.println("add Operation in Queue: " + queue.add("hitesh"));
        System.out.println("Elements in Queue: " + queue);
        System.out.println();
        System.out.println("element Operation in Queue: " + queue.element());
        System.out.println("Elements in Queue: " + queue);
        System.out.println();

        System.out.println("peek Operation in Queue: " + queue.peek());
        System.out.println("Elements in Queue: " + queue);
        System.out.println();

        System.out.println("offer Operation in Queue: " + queue.offer("manoj"));
        System.out.println("Elements in Queue: " + queue);
        System.out.println();

        System.out.println("remove Operation in Queue: " + queue.remove());
        System.out.println("Elements in Queue: " + queue);
        System.out.println();

        System.out.println("poll Operation in Queue: " + queue.poll());
        System.out.println("Elements in Queue: " + queue);
    }
}
