package MultiThreading.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    List<String> list = new ArrayList<>();
    public synchronized void  producer(String data){
        list.add(data);
        System.out.println(" Produced " + data);
        notify();
    }
    public synchronized void consumer()  {
        while (list.isEmpty()) {
            try {
                System.out.println("Buffer empty, consumer waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String item = list.remove(0);
        System.out.println(" Comsumed "+ item);

    }

    public static void main(String[] args) {
        Demo d = new Demo();
        Thread producer= new Thread(()->{
           for (int i=1;i<=5;i++){
               d.producer(" data is "+i);
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread consmer= new Thread(()->{
            for (int i=1;i<=5;i++) {
                d.consumer();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        producer.start();
        consmer.start();
    }
}
