package com.lt.vbank.service;

import com.lt.vbank.model.Account;

public interface AccountService {

    void createAccount(String name, String type);

    void deleteAccount(String name, String type);

    void updateAccount(String name, String type, String newName);

    void printAccounts();

    void printAccounts(String type);

    void printAccountsWhereTypeLike(String typeLike);

    void printAccountTypeInfo();
}
