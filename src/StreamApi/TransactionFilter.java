package StreamApi;

import java.util.*;
import java.util.stream.*;

class Transaction {
    private String source;
    private double amount;

    public Transaction(String source, double amount) {
        this.source = source;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{source='" + source + "', amount=" + amount + "}";
    }
}

public class TransactionFilter {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("amazon", 1500),
                new Transaction("apple", 900),
                new Transaction("airbnb", 2000),
                new Transaction("flipkart", 2500),
                new Transaction("axis", 800)
        );

        List<Transaction> result = transactions.stream()
                .filter(t -> t.getAmount() > 1000)                     // ✅ condition 1: amount > 1000
                .filter(t -> t.getSource().toLowerCase().startsWith("a")) // ✅ condition 2: source starts with 'a'
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}

