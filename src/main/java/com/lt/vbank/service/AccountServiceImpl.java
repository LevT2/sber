package com.lt.vbank.service;

import com.lt.vbank.dto.AccountTypeInfo;
import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;
import com.lt.vbank.repository.AccountRepository;
import com.lt.vbank.repository.AccountTypeRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.joda.time.DateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Consumer;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    AccountTypeRepository accountTypeRepo;




    @Transactional
    public AccountType checkAccountType(String name) {
        Optional<AccountType> accountTypeOptional = accountTypeRepo.findByName(name);
        accountTypeOptional
                .orElseThrow(NullPointerException::new);

        return accountTypeRepo.findByName(name).get(); // возьмём из наличия
    }


    private AccountType getExistentAccountType(String name){
        AccountType accountType = null;
        try {
            accountType = checkAccountType(name);
        } catch (NullPointerException ex) {
            System.out.println("\n ERROR: Invalid Account_Type: "+name);
        }
        return accountType;
    }



    @Override
    public void createAccount(String name, String type, LocalDate localDate) {
        AccountType accountType = getExistentAccountType(type);
        if (null == accountType) return;

        Account account = new Account(name);
        account.setAccountType(accountType);

        LocalDateTime localDateTime = localDate.atTime(12,0);

        account.setDateAcc(
                java.sql.Timestamp.valueOf(localDateTime));

        accountRepo.saveAndFlush(account);
        accountRepo.clear();
    }


    public void createAccount(String name, String type) {
        AccountType accountType = getExistentAccountType(type);
        if (null == accountType) return;

        Account account = new Account(name);
        account.setAccountType(accountType);

        ZoneId timeZone = ZoneId.of("Europe/Moscow");
        account.setDateAcc(
                java.sql.Timestamp.valueOf(
                        LocalDateTime.now(timeZone)));

        accountRepo.saveAndFlush(account);
        accountRepo.clear();
    }


    @Override
    public void deleteAccount(String name, String type) {
        AccountType accountType = getExistentAccountType(type);
        if (null == accountType) return;

        accountRepo.deleteByNameAndAccountType_Id(
                name,accountType.getId());

        accountRepo.flush();
        accountRepo.clear();
    }

    @Override
    public void updateAccount(String name, String type, String newName){
        AccountType accountType = getExistentAccountType(type);
        if (null == accountType) return;

        Account account = accountRepo.findByNameAndAccountType_Id(
                name,accountType.getId());

        account.setName(newName);

        accountRepo.saveAndFlush(account);
        accountRepo.clear();
        logger.info("New name set: {}",
                accountRepo.findByNameAndAccountType_Id(newName,
                        accountType.getId()).getName());
    }


    @Override
    public void printAccounts(){
        accountRepo.findAll().
                forEach(System.out::println);
    }


    @Override
    public void printAccounts(String type){
        AccountType accountType = getExistentAccountType(type);
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
