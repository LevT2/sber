package com.lt.vbank.repository;

import com.lt.vbank.model.Account;
import com.lt.vbank.model.AccountType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
//@Transactional
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void refresh (Account account) {
        em.refresh(account);
    }


    private int lookupAccountType_Id(String name) throws RuntimeException {
        int accountTypeId;
        switch (name) {
            case "DEBIT":
                accountTypeId = 1;
                break;
            case "CREDIT":
                accountTypeId = 2;
                break;
            default:
                throw new RuntimeException("Invalid account type name");
        }
        return accountTypeId;
    }



    @Override
    public AccountType getAccountTypeByName(String name) {
        return em.find(AccountType.class,lookupAccountType_Id(name));
    }

    public void clear(){
        //Однако  https://stackoverflow.com/questions/13886608/when-to-use-entitymanager-clear
        em.clear();
    }


}
