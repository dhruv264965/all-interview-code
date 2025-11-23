package MultiThreading.Practics;

public class CreateThread {
    public static void main(String[] args) {
        Runnable runnableObjct =  () ->{
            System.out.println("thread is started");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().getName();
            System.out.println("Thread is completed");

        };

        Thread objThread=new Thread(runnableObjct);
        //objThread.setDaemon(true);
        objThread.start();
        System.out.println("Main thread");



    }
}
