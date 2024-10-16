package dev.bank;

import dev.dto.Transaction;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount {

    public enum AccountType{
        CHECKING, SAVING;
    }

    private final AccountType type;
    private double balance;
    //i choose map because make sense to have the map ordered, by the order they were added
    private Map<Long, Transaction> transactions = new LinkedHashMap<>();

    public BankAccount(AccountType type, double balance) {
        this.type = type;
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }


//    public Map<Long, Transaction> getTransactions() {
//       // return transactions;
//        //instead  i will return unmodifiable collection
//        return Map.copyOf(transactions);
//    }

      public Map<Long, String> getTransactions() {
       Map<Long, String> txMap = new LinkedHashMap<>();
       for (var tx : transactions.entrySet()){
           txMap.put(tx.getKey(), tx.getValue().toString());
       }
       return txMap;
    }

    //package private
    void commitTransactions(int routingNumber,long transactionId, String customerId, double amount){
        //assume validation occured already
        balance += amount;
        transactions.put(transactionId, new Transaction(routingNumber,transactionId, Integer.parseInt(customerId), amount));
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(type, balance);
    }
}
