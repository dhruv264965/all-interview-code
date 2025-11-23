package MultiThreading.Practics;

public class sleep extends Thread{
    @Override
    public void run() {
       for(int i=0;i<10;i++){
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(i);
       }

    }

    public static void main(String[] args) {
        sleep t1= new sleep();
        t1.start();
    }
}
