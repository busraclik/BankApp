import dev.bank.Bank;
import dev.bank.BankAccount;
import dev.bank.BankCustomer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        Bank bank = new Bank(23564);
        bank.addCustomer("joe", 500.0, 10000.00);


        BankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);
        //cant do this (unmodifiable)
//        List<BankAccount> accounts = joe.getAccounts();
//        accounts.clear();
//        System.out.println(joe);


        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, 30)){
            System.out.println(joe);
        }
        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, -30)){
            System.out.println(joe);
        }
//test

        BankAccount checking = joe.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k,v) -> System.out.println(k + ": " + v));
//
//        System.out.println("-------------");
//        for (var tx : transactions.values()){
//            tx.setCustomerId(2);
//            tx.setTransactionAmount(10000.0);
//        }
//
//        transactions.forEach((k,v) -> System.out.println(k + ": " + v));


        //tyr to clear joes transactions
        //this code compiles and runs but has no affects on joes transactions
        //passed back a modifiable collection with immutable data
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().clear();

        //here we can see client can change data on our bank customers account
       //we need deep cop(we need also each individual transactions copey)
        System.out.println("--------------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions()
                .forEach((k,v) -> System.out.println(k + ": " + v));
    }
}