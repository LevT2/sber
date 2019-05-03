package com.lt.vbank;

import com.lt.vbank.repository.AccountRepositoryImpl;
import com.lt.vbank.repository.AccountTypeRepository;
import com.lt.vbank.service.AccountService;
import com.lt.vbank.service.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VbankApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    public VbankApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(VbankApplication.class, args);
    }

    @Autowired
    private AccountService accountService;

    @Autowired
    AccountTypeRepository accountTypeRepository;


    @Override
    public void run(String... args) {
        System.out.println();
        System.out.println("Precreated account types:");
        accountTypeRepository.findAll().forEach(System.out::println);

        // added base error handling for missing AccountType: see AccountServiceImpl
        // @Transactional public AccountType checkAccountType(String name)
        System.out.println();
        logger.info("Initializing client accounts");
        accountService.createAccount("IVANOV","DEBIT");
        accountService.createAccount("PETROV","CREDIT");
        accountService.createAccount("SIDOROV","DEBIT");
        dump();

        System.out.println();
        System.out.println("\n Count of client Accounts grouped by AccountType:");
        accountService.printAccountTypeInfo();

        System.out.println("\n  List of debit accounts:");
        accountService.printAccounts("DEBIT");

        System.out.println();
        logger.info("Deleting account SIDOROV");
        accountService.deleteAccount("SIDOROV","DEBIT");
        dump();

        //don't try to rename missing user
        //TODO  will fix after adding advanced test infrastructure
        System.out.println();
        logger.info("Renaming account IVANOV");
        accountService.updateAccount("IVANOV", "DEBIT","IVANOV IVAN");
        dump();

        System.out.println();
        logger.info("Printing accounts where type like C%");
        accountService.printAccountsWhereTypeLike("C%");

    }

    private void dump() {
        System.out.println("\n  Client accounts dump:");
        accountService.printAccounts();
    }
}
