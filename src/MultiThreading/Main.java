package MultiThreading;
/******************************************************************************

 Online Java Compiler.
 Code, Compile, Run and Debug java program online.
 Write your code in this editor and press "Run" button to execute it.

 *******************************************************************************/
public class Main
{
    public static void main(String[] args) {
        System.out.println("Hello World");
        Object lock  =new Object();
        t1 a=new t1(lock);
        t2 b=new t2(lock);
        a.start();
        b.start();
    }
}
class t1 extends Thread{
    private final Object lock;
    public t1(Object lock){
        this.lock=lock;
    }
    @Override
    public void run(){
        for(int i=1;i<=10;i++){
            if(i%2!=0){
                synchronized(lock) {
                    System.out.println("odd "+i);
                    lock.notify();
                    if(i<9){
                        try {
                            lock.wait() ;
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }
}
class t2 extends Thread{
    private final Object lock;
    public t2(Object lock){
        this.lock=lock;
    }
    @Override
    public void run(){
        for(int i=1;i<=10;i++){
            if(i%2==0){
                synchronized(lock) {
                    System.out.println("even "+i);
                    lock.notify();
                    if(i<=10){
                        try {
                            lock.wait() ;
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }
}

