package com.lt.vbank.service;

import com.lt.vbank.dto.AccountTypeInfo;
import com.lt.vbank.exceptions.BadAccountNameException;
import com.lt.vbank.exceptions.BadAccountTypeException;
import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;
import com.lt.vbank.repository.AccountRepository;
import com.lt.vbank.repository.AccountTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    AccountTypeRepository accountTypeRepo;




//    @Transactional
    public AccountType checkAccountType(String name) throws BadAccountTypeException {
        Optional<AccountType> accountTypeOptional = accountTypeRepo.findByName(name);
        accountTypeOptional
                .orElseThrow(BadAccountTypeException::new);

        return accountTypeRepo.findByName(name).get(); // возьмём из наличия
    }


    private Account getAccount(String name, String type) throws BadAccountNameException {
        Optional<Account> accountOptional = accountRepo.findByNameAndAccountType_Name(name,type);
        accountOptional.orElseThrow(BadAccountNameException::new);

        return accountRepo.findByNameAndAccountType_Name(name,type).get();  // возьмём из наличия
    }


    private AccountType getAccountType(String name){
        AccountType accountType = null;
        try {
            accountType = checkAccountType(name);
        } catch (NullPointerException ex) {
            System.out.printf("%n  %s: %s %n", ex.getMessage(), name);
        }
        return accountType;
    }


//    private AccountType getAccountType(String name){
//        AccountType accountType = null;
//        try {
//            accountType = accountTypeRepo.getByName(name);
//        } catch  (BadAccountTypeException ex) {
//            System.out.printf("%n  %s: %s %n", ex.getMessage(), name);
//        }
//        return accountType;
//    }



//    private Account getAccount(String name, String type) {
//        Account account = null;
//
//
//        if (null == getAccountType(type)) {return;}
//
//        Account account = null;
//
//        try {
//            account = accountRepo.getByNameAndAccountType_Id(
//                    name, accountTypeRepo.getByName(type).getId());
//        } catch (NullPointerException ex) {
//            System.out.println("\n ERROR: Invalid Parameter(s)"); // : "+name);
//        }
//        return account;
//    }


    @Override
    public void createAccount(String name, String type, LocalDate localDate) {
        AccountType accountType = getAccountType(type);

        Account account = new Account(name);
        account.setAccountType(accountType);

        LocalDateTime localDateTime = localDate.atTime(12,0);

        account.setDateAcc(
                java.sql.Timestamp.valueOf(localDateTime));

        accountRepo.saveAndFlush(account);
        accountRepo.clear();
    }


//    public void createAccount(String name, String type) {
//        AccountType accountType = getAccountType(type);
//        if (null == accountType) return;
//
//        Account account = new Account(name);
//        account.setAccountType(accountType);
//
//        ZoneId timeZone = ZoneId.of("Europe/Moscow");
//        account.setDateAcc(
//                java.sql.Timestamp.valueOf(
//                        LocalDateTime.now(timeZone)));
//
//        accountRepo.saveAndFlush(account);
//        accountRepo.clear();
//    }


    @Override
    public void deleteAccount(String name, String type)  {
        if (null == getAccountType(type)) {return;}

          try {
              Account account = getAccount(name, type);
          } catch (BadAccountNameException ex){
              System.out.printf("%n  %s: %s %n", ex.getMessage(), name);
          }

        accountRepo.deleteByNameAndAccountType_Name(name,type);
        accountRepo.flush();
        accountRepo.clear();
    }

    @Override
    public void updateAccount(String name, String type, String newName){
        Account account = null;
        if (null == getAccountType(type)) {return;}

        try {
            account = getAccount(name, type);
            account.setName(newName);
            accountRepo.saveAndFlush(account);
            accountRepo.clear();

            logger.info("New name set: {}", getAccount(newName,type).getName());

        } catch (BadAccountNameException ex){
            System.out.printf("%n  %s: %s %n", ex.getMessage(), name);
        }
    }


    @Override
    public void printAccounts(){
        accountRepo.findAll().
                forEach(System.out::println);
    }


    @Override
    public void printAccounts(String type){
        AccountType accountType = getAccountType(type);
        if (null == accountType) return;

        accountRepo.findAllByAccountType_Name(type).
                forEach(System.out::println);
    }

    @Override
    public void printTodaysAccounts() {
        accountRepo.findAllByDateAccIsToday().
                forEach(System.out::println);
    }

    @Override
    public void printSelectedInfofromEntities(){
        Consumer<Account> printSelected = account -> System.out.printf(
                "Account (selected fields): name= {%s}, dateAcc= {%s}%n",
                account.getName(),
                new org.joda.time.LocalDateTime(account.getDateAcc()).
                        toLocalDate().
                        toString());

        accountRepo.findAll().
                forEach(printSelected);
    }

    @Override
    public void printAccountsWhereTypeLike(String typeLike){
        accountRepo.findAllByAccountType_NameIsLike(typeLike).forEach(System.out::println);
    }

    @Override
    public void printAccountTypeInfo() {
        Iterable<AccountTypeInfo> info = accountRepo.getAccountTypeInfo();
        info.forEach(System.out::println);
    }
}
