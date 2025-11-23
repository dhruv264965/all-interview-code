package MultiThreading.Thread;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1= new Thread(()->{
            for(int i=1;i<=5;i++){
                System.out.println(i);
            }
         //   System.out.println(" I am Thread 1" + Thread.currentThread().getName());
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
       Thread t2=new Thread(()->{
           for(int i=6;i<=10;i++){
               System.out.println(i);
           }
       //    System.out.println(" I am Thread 2" + Thread.currentThread().getName());
       });
       t1.start();
       t1.join();  // before running the thread 2 it wait for 8 sec
       t2.start();

    }
}
