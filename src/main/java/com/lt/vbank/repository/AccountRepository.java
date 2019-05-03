package com.lt.vbank.repository;

import com.lt.vbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>, AccountRepositoryCustom {

    void deleteByNameAndAccountType_Id(String name, int accountTypeId);

    Account findByNameAndAccountType_Id(String name, int accountTypeId);

    Iterable<Account> findAllByAccountType_Name(String type);

    Iterable<Account> findAllByAccountType_NameIsLike(String typeLike);

    Optional<Account> findByName(String typeName);
}
