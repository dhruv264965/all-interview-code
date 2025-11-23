package MultiThreading.ThreadLocal;

public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<Integer>threadLocal=new ThreadLocal<>();
        Thread t1=new Thread(()->{
            threadLocal.set(100);
            System.out.println("Thread 1 Value: "+threadLocal.get());
            threadLocal.remove(); //for good practics
        });
        Thread t2=new Thread(()->{
            threadLocal.set(200);
            System.out.println("Thread 2 Value: "+threadLocal.get());
            threadLocal.remove();
        });
        t1.start();
        t2.start();

    }
}
