package com.lt.vbank.service;

import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;
import com.lt.vbank.repository.AccountRepository;
//import com.lt.vbank.repository.AccountTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService { // ,AccountService_mod7 {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountRepository accountRepo;


//    @Autowired
//    public AccountServiceImpl(AccountRepository accountRepo) { //, AccountTypeRepository accountTypeRepository) {
//        this.accountRepo = accountRepo;
////        this.accountTypeRepository = accountTypeRepository;
//    }



    @Override
    public void createAccount(String name, String accountTypeName) {
        Account account = new Account(name);
        AccountType accountType = accountRepo.getAccountTypeByName(accountTypeName);
        account.setAccountType(accountType);

        saveAccount(account);
    }

    @Override
    public void deleteAccount(String name, String accountTypeName) {
        AccountType accountType = accountRepo.getAccountTypeByName(accountTypeName);
        accountRepo.deleteByNameAndAccountType_Id(name,accountType.getId());
        logger.info("Size of repo {}", accountRepo.count());
    }

    @Override
    public void updateAccount(String name, String accountTypeName, String newName){
        AccountType accountType = accountRepo.getAccountTypeByName(accountTypeName);
        Account account = accountRepo.findByNameAndAccountType_Id(name,accountType.getId());
        account.setName(newName);

        saveAccount(account);
        logger.info("New name set: {}", accountRepo.findByNameAndAccountType_Id(newName,accountType.getId()).getName());
    }


    private void saveAccount(Account account) {
        accountRepo.save(account);
        logger.info("Size of repo {}", accountRepo.count());
    }


    public Iterable<Account> getAccounts() {
        return accountRepo.findAll();
    }

    public void printAccounts(){
        //getAccounts().forEach(account -> System.out.println(account.getName()));
        getAccounts().forEach(System.out::println);
    }

//    @Override
//    public void clearCache(){
//        accountRepo.getEn.clear();
//    }


//    public Account getAccount(int id) {
//        return accountRepo.findOne(id);
//    }
}
