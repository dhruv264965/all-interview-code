package MultiThreading.Practics;

public class RunnableDemo {
    public static void main(String[] args) {
        MyTask myTask=new MyTask();
        Thread thread=new Thread(myTask);// runnable interface does not have start method that by we need to create object of thread class and pass.
        thread.setName("worker 0");
        thread.start();

        Thread thread1=new Thread(myTask); //same object can be used
        thread1.setName("worker 1");
        thread1.start();

        Thread thread2=new Thread(new Runnable() {   //lamda expression also can be used  and it will extend run() method.
            @Override
            public void run() {
                System.out.println("Default  "+Thread.currentThread().getName());
            }

        });
        thread2.setName("worker 2");
        thread2.start();
    }
}
