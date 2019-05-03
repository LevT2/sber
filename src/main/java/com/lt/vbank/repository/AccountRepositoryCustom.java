package com.lt.vbank.repository;

import com.lt.vbank.dto.AccountTypeInfo;
import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;

import java.util.List;

public interface AccountRepositoryCustom {
    void refresh(Account account);

    void clear();

    List<AccountTypeInfo> getAccountTypeInfo();
}
