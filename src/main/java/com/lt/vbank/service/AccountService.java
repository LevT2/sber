package com.lt.vbank.service;


import java.time.LocalDate;

public interface AccountService {

    void createAccount(String name, String type, LocalDate localDate);

    void deleteAccount(String name, String type);

    void updateAccount(String name, String type, String newName);


    void printAccounts();


    // задание 7.3
    void printSelectedInfofromEntities();

    // задание 7.4
    void printTodaysAccounts();

    // задание 7.5
    void printAccounts(String type);

    // задание 7.6
    void printAccountTypeInfo();

    // задание 7.7
    void printAccountsWhereTypeLike(String typeLike);

}
