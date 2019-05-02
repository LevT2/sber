package com.lt.vbank.service;

import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;
import com.lt.vbank.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepo;

    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }


    public void createAccount(String name, String accountTypeName) {
        Account account = new Account(name);
        AccountType accountType = accountRepo.getAccountTypeByName(accountTypeName);
        account.setAccountType(accountType);

        createAccount(account);
    }




    @Override
    public void createAccount(Account account) {
        accountRepo.save(account);
        logger.info("Size of repo {}", accountRepo.count());
    }

    @Override
    public void updateAccount(Account account, String newName) {
//        accountRepo.findById(id);
        account.setName(newName);
        accountRepo.save(account);
        logger.info("Size of repo {}", accountRepo.count());
    }

    @Override
    public void deleteAccount(int id) {
        accountRepo.deleteById(id);
        logger.info("Size of repo {}", accountRepo.count());
    }

    @Override
    public Iterable<Account> getAccounts() {
        return accountRepo.findAll();
    }

//    public Account getAccount(int id) {
//        return accountRepo.findOne(id);
//    }
}
