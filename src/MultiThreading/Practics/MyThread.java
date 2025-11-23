package MultiThreading.Practics;

import java.util.Scanner;

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("hello1 "+Thread.currentThread());
        System.out.println("enter your name");
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println("hello2 "+Thread.currentThread().getName());
    }
}
