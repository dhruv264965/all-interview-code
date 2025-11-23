package MultiThreading.Practics;
class old extends Thread{

    @Override
    public void run() {
        for(int i=1;i<1000;i+=2){
            System.out.println("T1 is odd "+i);
        }
    }
}
class even extends Thread{
    @Override
    public void run() {
        for(int i=2;i<=1000;i+=2){
            System.out.println("T2 is even "+i);
        }
    }
}
public class join {

    public static void main(String[] args) throws InterruptedException {
        old t1=new old();
        even t2=new even();
      //  t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        try {
            // Wait for Thread1 to finish before starting Thread2
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();


    }
}
