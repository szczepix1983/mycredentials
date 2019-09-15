package com.szczepix.credentials.dao;

import com.szczepix.credentials.entities.LoginEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginRepository extends CrudRepository<LoginEntity, Integer> {
}

