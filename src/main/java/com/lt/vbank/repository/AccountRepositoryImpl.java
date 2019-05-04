package com.lt.vbank.repository;

import com.lt.vbank.dto.AccountTypeInfo;
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

    @Override
    public void clear(){
        //Однако  https://stackoverflow.com/questions/13886608/when-to-use-entitymanager-clear
        em.clear();
    }

    @Override
    public Iterable<AccountTypeInfo> getAccountTypeInfo() {
        return em.createQuery(
                "select new com.lt.vbank.dto.AccountTypeInfo(" +
                        "at.name, size(at.accounts)) " +
                        "from AccountType at " +
                        "group by at.name ")
                .getResultList();
    }

}
