package MultiThreading.RaceCondition;

import java.util.concurrent.atomic.AtomicInteger;

public class VisitorsCount implements  Runnable{// How to prevent 1- Use AtomicInteger class
                                                                  // 2-Use Synchorization keyword
    private int visitor;
   // private AtomicInteger visitor;

    public VisitorsCount() {
        this.visitor = 0;
//        this.visitor=new AtomicInteger();
    }

    public int getVisitor() {
        return visitor;
//        return  visitor.intValue();
    }

    @Override
    public void run() {
        //visitor++;
//        visitor.increme/ntAndGet();
        incrementVisitor();
    }
    private synchronized void incrementVisitor(){
         visitor++;
    }


}
