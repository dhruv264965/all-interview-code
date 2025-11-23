package MultiThreading.Locks;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount =new BankAccount();
        Runnable mytask= new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);
            }
        };
    }
}
