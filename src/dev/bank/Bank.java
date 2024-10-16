package dev.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
   public final int routingNUmber;
   private long lastTransactionId = 1;
   private final Map<String, BankCustomer> customers;

    public Bank(int routingNUmber) {
        this.routingNUmber = routingNUmber;
        customers = new HashMap<>();
    }

    public BankCustomer getCustomer(String id){
        BankCustomer customer = customers.get(id);
          return customer;
    }

    public void addCustomer (String name, double checkingInitialDepozit, double savingInitialDepozit){
       BankCustomer customer = new BankCustomer(name, checkingInitialDepozit, savingInitialDepozit);
       customers.put(customer.getCustomerId(), customer);
    }
    public boolean doTransaction(String id, BankAccount.AccountType accountType, double amount){
        BankCustomer customer = customers.get(id);
        if (customer != null){
           BankAccount account = customer.getAccount(accountType);
           if (account != null){
               if ((account.getBalance() + amount <  0)){
                   System.out.println("insufficient found");
               }else {
                   account.commitTransactions(routingNUmber, lastTransactionId++, id, amount);
                   return true;
               }
           }
        }else{
            System.out.println("ınvalid customer id");
        }
        return false;
    }

}
