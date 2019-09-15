package com.szczepix.credentials.dao;

import com.szczepix.credentials.entities.SettingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISettingRepository extends CrudRepository<SettingEntity, Integer> {

}
