package com.lt.vbank.service;

import com.lt.vbank.model.Account;

public interface AccountService {

    void createAccount(String name, String accountTypeName);

    void deleteAccount(String name, String accountTypeName);

    void updateAccount(String name, String accountTypeName, String newName);

    public void printAccounts();


//    void deleteAccount(int id);
//
//    Iterable<Account> getAccounts();

    //Account getAccount(int id);
}
