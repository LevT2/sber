package com.lt.vbank;

import com.lt.vbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VbankApplication implements CommandLineRunner {

    public VbankApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(VbankApplication.class, args);
    }

    @Autowired
    private AccountService accountService;


    @Override
    public void run(String... args) {
        accountService.createAccount("IVANOV","DEBIT");
        accountService.createAccount("PETROV","CREDIT");
        accountService.createAccount("SIDOROV","DEBIT");

        accountService.printAccounts();

//        accountService.deleteAccount(2);
        accountService.deleteAccount("SIDOROV","DEBIT");
        accountService.printAccounts();

        accountService.updateAccount("IVANOV", "DEBIT","IVANOV IVAN");
        accountService.printAccounts();



    }


}
