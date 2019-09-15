package com.szczepix.credentials.services.loginService;

import com.szczepix.credentials.dao.ILoginRepository;
import com.szczepix.credentials.entities.LoginEntity;
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
public class LoginService implements ILoginService {

    private final ILoginRepository repository;

    private final IEventSerivce eventSerivce;

    @Autowired
    public LoginService(final ILoginRepository repository, @Qualifier("eventService") final IEventSerivce eventSerivce) {
        this.repository = repository;
        this.eventSerivce = eventSerivce;
    }

    @Override
    public List<LoginEntity> getEntities() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void save(LoginEntity entity) {
        repository.save(entity);
    }
}
