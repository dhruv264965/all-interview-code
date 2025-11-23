package MultiThreading.Synchronization;

public class Counter {
    private int count=0;
    public  synchronized void increment(){
        count++;
    }
    //public void increment(){  // output will not accurate
    //        count++;
    //    }
    public int getCount() {
        return count;
    }
}
