package JAVA8Features;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class PrintOddEven {
   public static Object object =new Object();
   public static IntPredicate isEven=e->e%2==0;
    public static IntPredicate isOdd=e->e%2!=0;
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(()->PrintOddEven.printNumber(isOdd));
        CompletableFuture.runAsync(()->PrintOddEven.printNumber(isEven));
        Thread.sleep(1000);
    }
    public static void printNumber(IntPredicate condition){
        IntStream.range(1,10).filter(condition).forEach(PrintOddEven::excute);
    }


    public static void excute(int number){
        synchronized (object){

            try {
                System.out.println("ThreadName: "+Thread.currentThread().getName()+" "+ number);
                object.notify();
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
