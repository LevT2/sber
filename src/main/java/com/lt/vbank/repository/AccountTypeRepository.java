package com.lt.vbank.repository;

import com.lt.vbank.exceptions.BadAccountTypeException;
import com.lt.vbank.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType,Integer> {
    Optional<AccountType> findByName(String typeName);
//    AccountType getByName(String typeName) throws NullPointerException;
}
