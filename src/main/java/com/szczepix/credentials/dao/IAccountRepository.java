package com.szczepix.credentials.dao;

import com.szczepix.credentials.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<AccountEntity, Integer> {
}
