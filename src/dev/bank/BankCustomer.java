package dev.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {
    private final String name;
    private int lastId = 10_000_000;
    private final int customerId;
    private List<BankAccount> accounts = new ArrayList<>();

     BankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerId = lastId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVING,savingsAmount));
    }

    public String getName() {
        return name;
    }

    //015: Ensures the number is padded with leading zeros to a width of 15 characters.
    public String getCustomerId() {
        return "%015d".formatted(customerId);
    }

    public List<BankAccount> getAccounts() {
        //return new ArrayList<>(accounts);
        //return unmodifiable collection by using copy of method
        //this will prevent any clients from adding, deleting or reassigning accounts among other things

        return List.copyOf(accounts);
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public BankAccount getAccount(BankAccount.AccountType type){
         for (var account : accounts){
             if (account.getType() == type){
                 return account;
             }
         }
         return null;
    }

    @Override
    public String toString() {
       String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "customer: %s (id:%015d)%n\t%s%n".formatted(name, customerId,
                String.join("\n\t",accountStrings));
    }
}
