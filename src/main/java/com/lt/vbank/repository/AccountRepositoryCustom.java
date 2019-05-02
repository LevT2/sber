package com.lt.vbank.repository;

import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;

public interface AccountRepositoryCustom {
    void refresh(Account account);

    AccountType getAccountTypeByName(String name);
}