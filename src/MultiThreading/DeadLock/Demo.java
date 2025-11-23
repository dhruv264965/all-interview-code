package MultiThreading.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();
    public static void main(String[] args) {
        Thread t1= new Thread(()->{
            try {
                if(lock1.tryLock(100, TimeUnit.MILLISECONDS)){
                    System.out.println(" lock 1 acquire");
                    try {


                    if(lock2.tryLock(100,TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println("lock 2 acquire ");
                        }
                        finally {
                            lock2.unlock();
                        }

                    }
                    else {
                        System.out.println(" hello");
                    }
                }finally {
                        lock1.unlock();
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread t2= new Thread(()->{
            try {
                if(lock2.tryLock(100, TimeUnit.MILLISECONDS)){
                    System.out.println(" lock 2 acquire");
                    try {


                        if(lock1.tryLock(100,TimeUnit.MILLISECONDS)){
                            try {
                                System.out.println("lock 1 acquire ");
                            }
                            finally {
                                lock1.unlock();
                            }

                        }
                        else {
                            System.out.println(" hello");
                        }
                    }finally {
                        lock2.unlock();
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t1.start();
        t2.start();
    }
}
