package MultiThreading.Thread;

public class OddEvenPrint {
    public static final Object lock=new Object();
    public static int max=10;
    public static int number=1;
    public static void main(String[] args) {
      Thread t1=new Thread(()->{
          while (number<=max){
              synchronized (lock){
                  if(number%2==1){
                      System.out.println(" odd "+ number);
                      number++;
                      lock.notify();
                  }
                  else {
                      try {
                          lock.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          }
      });

      Thread t2=new Thread(()->{
          while (number<=max){
              synchronized (lock){
                  if(number%2==0){
                      System.out.println("Even "+number);
                      number++;
                      lock.notify();
                  }
                  else {
                      try {
                          lock.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          }
      });
      t1.start();
      t2.start();
    }
}
