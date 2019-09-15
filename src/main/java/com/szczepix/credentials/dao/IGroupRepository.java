package com.szczepix.credentials.dao;

import com.szczepix.credentials.entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupRepository extends CrudRepository<GroupEntity, Integer> {
}

