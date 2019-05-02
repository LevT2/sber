package com.lt.vbank.repository;

import com.lt.vbank.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer>, AccountRepositoryCustom {
}
