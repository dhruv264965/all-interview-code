package MultiThreading.Synchronization;




class counteri{
    int count=0;
    public synchronized void increment(){
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        counteri c=new counteri();

      Thread t1= new Thread(()->{
         for(int i=0;i<1000;i++){
            c.increment();
         }
      });
      Thread t2=new Thread(()->{
          for (int i=0;i<1000;i++){
              c.increment();
          }
      });
      t1.start();
      t2.start();
      t1.join();
      t2.join();
        System.out.println(c.getCount());
    }
}
