package com.lt.vbank.repository;

import com.lt.vbank.dto.AccountTypeInfo;
import com.lt.vbank.model.Account;

public interface AccountRepositoryCustom {
    void refresh(Account account);

    void clear();

    Iterable<AccountTypeInfo> getAccountTypeInfo();
}
