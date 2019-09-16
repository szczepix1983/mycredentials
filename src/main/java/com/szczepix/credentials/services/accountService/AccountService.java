package com.szczepix.credentials.services.accountService;

import com.szczepix.credentials.dao.IAccountRepository;
import com.szczepix.credentials.entities.AccountEntity;
import com.szczepix.credentials.events.AccountsChangeEvent;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountService implements IAccountService {

    private final IAccountRepository repository;

    private final IEventSerivce eventSerivce;

    @Autowired
    public AccountService(final IAccountRepository repository, @Qualifier("eventService") final IEventSerivce eventSerivce) {
        this.repository = repository;
        this.eventSerivce = eventSerivce;
    }

    @Override
    public List<AccountEntity> getEntities() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void save(final AccountEntity entity) {
        repository.save(entity);
        eventSerivce.dispatch(new AccountsChangeEvent());
    }
}
