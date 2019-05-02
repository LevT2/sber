package com.lt.vbank.service;

import com.lt.vbank.model.Account;

public interface AccountService {

    void createAccount(String name, String accountTypename);

    void createAccount(Account account);

    void updateAccount(Account account, String newName);

    void deleteAccount(int id);

    Iterable<Account> getAccounts();

    //Account getAccount(int id);
}
