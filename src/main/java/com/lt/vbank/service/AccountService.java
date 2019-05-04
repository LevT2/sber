package com.lt.vbank.service;


import java.time.LocalDate;

public interface AccountService {

    void createAccount(String name, String type, LocalDate localDate);

    void deleteAccount(String name, String type);

    void updateAccount(String name, String type, String newName);

    void printAccounts();

    void printAccounts(String type);

    void printSelectedInfofromEntities();

    void printTodaysAccounts();

    void printAccountsWhereTypeLike(String typeLike);

    void printAccountTypeInfo();
}
