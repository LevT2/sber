package com.lt.vbank.service;

import com.lt.vbank.dto.AccountTypeInfo;
import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;
import com.lt.vbank.repository.AccountRepository;
import com.lt.vbank.repository.AccountTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public void createAccount(String name, String type) {
        AccountType accountType = getExistentAccountType(type);
        if (null == accountType) return;

        Account account = new Account(name);
        account.setAccountType(accountType);

        accountRepo.save(account);
        accountRepo.clear();  //TODO повторить тему
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
        logger.info("New name set: {}", accountRepo.findByNameAndAccountType_Id(newName,accountType.getId()).getName());
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
    public void printAccountsWhereTypeLike(String typeLike){
        accountRepo.findAllByAccountType_NameIsLike(typeLike).forEach(System.out::println);
    }

    @Override
    public void printAccountTypeInfo() {
        List<AccountTypeInfo> info = accountRepo.getAccountTypeInfo();
        info.forEach(System.out::println);
    }
}
