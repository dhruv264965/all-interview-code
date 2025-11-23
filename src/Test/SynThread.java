package Test;

public class SynThread {
    public void printTable(int n) {
        for (int i = 0; i < 5; i++) {
            System.out.println(n * i);
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
        static class MyThread1 extends Thread {
            SynThread t;

            MyThread1(SynThread t) {
                this.t = t;
            }

            public void run() {
                t.printTable(5);
            }
        }
        static class MyThread2 extends Thread {
            SynThread t;

            MyThread2(SynThread t) {
                this.t = t;
            }

            public void run() {
                t.printTable(100);
            }
        }

        public static void main (String[]args){
            SynThread s = new SynThread();
            MyThread1 t1 = new MyThread1(s);
            MyThread2 t2 = new MyThread2(s);
            t1.start();
            t2.start();
        }
    }


