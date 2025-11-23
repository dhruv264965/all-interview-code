package MultiThreading.Runnable;

public class RunnableDemo {
    public static void main(String[] args) {
        Runnable t1= ()->{
            System.out.println(" helloe "+Thread.currentThread().getName());
        };
        Thread t=new Thread(t1);
        t.start();
        Runnable t2=()->{
            System.out.println(" hii" + Thread.currentThread().getName());
        };
       Thread runn=new Thread(t2);
       runn.start();
    }
}
