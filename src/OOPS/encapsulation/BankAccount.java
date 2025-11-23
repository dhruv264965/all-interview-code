package OOPS.encapsulation;

public class BankAccount {
    private long accountNumber;
    private double balance;
    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("deposited "+ balance);
        }
        else {
            System.out.println("invalid deposit amount");
        }
    }
    public void withdraw(double amount){
        if(amount>0 && amount<=balance){
            balance-=amount;
            System.out.println("withdraw "+amount);
        }
        else {
            System.out.println("invalid withdraw amount");
        }

    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
}
