package MultiThreading.Volatiles;
class Task implements Runnable{
private volatile boolean running= true;     // not volatile now
    @Override
    public void run() {
        System.out.println(" task started");
        while (running){

        }
        System.out.println(" task stop");
    }

    public void stop(){
        running=false;
    }


}
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
    Task task =new Task();
    Thread t1=new Thread(task);
    t1.start();
    Thread.sleep(1000);
        System.out.println("Requesting stop...");
        task.stop();
    }
}
