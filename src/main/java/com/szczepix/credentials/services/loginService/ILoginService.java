package com.szczepix.credentials.services.loginService;

import com.szczepix.credentials.entities.LoginEntity;

import java.util.List;

public interface ILoginService {

    List<LoginEntity> getEntities();

    void save(final LoginEntity entity);
}
