package com.lt.vbank.repository;

import com.lt.vbank.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType,Integer> {
    Optional<AccountType> findByName(String typeName);
}
