package MultiThreading;
public class PrintOddEvenInTwoDiffferentThread {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread1 t1 = new Thread1(lock);
        Thread2 t2 = new Thread2(lock);
        t1.start();
        t2.start();
    }
}

class  Thread1 extends Thread{
   private final Object lock;

    public Thread1(Object lock) {
        this.lock = lock;
    }



    @Override
   public void run(){
        for(int i=1;i<10;i++){
            if(i%2!=0){
           synchronized (lock){
               System.out.println("T1 is odd :"+ i);
               lock.notify();   // Notify T2 to proceed
               if(i<9){
                   try {
                       lock.wait(); //Wait for t2 to print even
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
            }
        }
    }
}

class Thread2 extends Thread{
    private final Object lock;

  public Thread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run(){
        for (int i=2;i<=10;i++){
            if(i%2==0){
                synchronized (lock){
                    System.out.println("T2 is even: "+i);
                    lock.notify();  // Notify T1 to proceed
                    if(i<=10){
                        try {
                            lock.wait();  //Wait for t1 to print odd
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}




