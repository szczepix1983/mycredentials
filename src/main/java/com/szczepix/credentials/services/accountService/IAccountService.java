package com.szczepix.credentials.services.accountService;

import com.szczepix.credentials.entities.AccountEntity;

import java.util.List;

public interface IAccountService {

    List<AccountEntity> getEntities();

    void save(final AccountEntity entity);
}
