package com.lt.vbank.repository;

import com.lt.vbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>, AccountRepositoryCustom {
    void deleteByNameAndAccountType_Id(String name, int accountTypeId);
    Account findByNameAndAccountType_Id(String name, int accountTypeId);
}
