package Test;

//public class MyThread extends Thread{
//    public void run()
//    {
//        int i=1;
//        while (true)
//        {
//            System.out.println(i+"hello");
//            i++;
//        }
//    }
//
//    public static void main(String[] args) {
//        MyThread t=new MyThread();
//        t.start();
//        int i=1;
//        while (true)
//        {
//            System.out.println(i+"hello");
//            i++;
//        }
//    }
//}
public class MyThread implements Runnable{
    public void run()
    {
        int i=1;
        while (true)
        {
            System.out.println(i+"hello");
            i++;
        }
    }

    public static void main(String[] args) {
        MyThread m=new MyThread();
        Thread t=new Thread(m);
        t.start();
        int i=1;
        while (true)
        {
            System.out.println(i+"hello");
            i++;
        }
    }
}
