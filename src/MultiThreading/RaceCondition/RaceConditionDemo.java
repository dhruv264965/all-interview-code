package MultiThreading.RaceCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceConditionDemo {
    public static void main(String[] args) {
        VisitorsCount task = new VisitorsCount();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4000; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total Number of Visitors  "+task.getVisitor()); // we are getting 3945 visitor so some vistior are lost.and this is RaceCondition

      //  System.out.println("Total Number of Visitors  " + task.getVisitor()); //we are getting 4000 becz- Using AtomicInteger class
//        System.out.println("Total Number of Visitors  " + task.getVisitor()); //we are getting 4000 becz- Using synchronized keyword
    }



}
