package MultiThreading.Practics;
class SharedResoure{
    private volatile boolean flag=false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean getFlag(){
        return flag;
    }
}
public class VolatileKeyword {
    public static void main(String[] args) {
     SharedResoure sharedResoure=new SharedResoure();
     // create Thread 1
        new Thread(()->{
            System.out.println("Thread 1 started");
            try {
                System.out.println("Thread 1 logic started");
                Thread.sleep(1000);
                System.out.println("Thread 1 logic completed");
                sharedResoure.setFlag(true);
                System.out.println("Flag set by Thread 1");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // create Thread 2
        new Thread(()->{
            System.out.println("Thread 2 is started ");
            while(!sharedResoure.getFlag()){

            }
            System.out.println("Thread 2 logic completed");
        }).start();
    }
}
