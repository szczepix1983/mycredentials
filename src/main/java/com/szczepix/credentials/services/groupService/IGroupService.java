package com.szczepix.credentials.services.groupService;

import com.szczepix.credentials.entities.GroupEntity;

import java.util.List;

public interface IGroupService {

    List<GroupEntity> getEntities();

    void save(final GroupEntity entity);
}
