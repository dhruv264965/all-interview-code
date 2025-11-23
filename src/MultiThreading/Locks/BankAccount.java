package MultiThreading.Locks;

public class BankAccount {
    private int balance=100;
    public void withdraw(int amount){
        if(balance>amount){
            System.out.println(Thread.currentThread().getName()+ " attempting to withdraw "+ amount);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance-=amount;
            System.out.println(Thread.currentThread().getName()+ " completed withdraw. Reamining balance "+ balance );

        }
        else {
            System.out.println(Thread.currentThread().getName()+ "Insuffient Balance");
        }
    }
}
