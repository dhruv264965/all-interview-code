package MultiThreading;
import java.util.LinkedList;
import java.util.Queue;

class SharedResourcee {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private boolean hasData;

    public SharedResourcee(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer interrupted");
            }
        }
        queue.add(value);
        hasData=true;
        System.out.println("Produced: " + value);
        notifyAll(); // Notify all waiting threads
    }

    public synchronized int consume() {
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Consumer interrupted");
            }
        }
        hasData=false;
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notifyAll(); // Notify all waiting threads
        return value;
    }
}

class Producerr implements Runnable {
    private final SharedResourcee resource;

    public Producerr(SharedResourcee resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.produce(i);
        }
    }
}

class Consumerr implements Runnable {
    private final SharedResourcee resource;

    public Consumerr(SharedResourcee resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.consume();
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args) {
        SharedResourcee resource = new SharedResourcee(5); // Buffer capacity of 5
        Thread producer1 = new Thread(new Producerr(resource));

        Thread consumer1 = new Thread(new Consumerr(resource));


        producer1.start();
        consumer1.start();

    }
}
