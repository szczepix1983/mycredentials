package com.szczepix.credentials.dao;

import com.szczepix.credentials.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    ProfileEntity findProfileByUserNameAndPassword(String username, String password);
}

