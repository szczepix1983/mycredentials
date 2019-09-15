package com.szczepix.credentials.services.groupService;

import com.szczepix.credentials.dao.IGroupRepository;
import com.szczepix.credentials.entities.GroupEntity;
import com.szczepix.credentials.events.GroupsChangeEvent;
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
public class GroupService implements IGroupService {

    private final IGroupRepository repository;

    private final IEventSerivce eventSerivce;

    @Autowired
    public GroupService(final IGroupRepository repository, @Qualifier("eventService") final IEventSerivce eventSerivce) {
        this.repository = repository;
        this.eventSerivce = eventSerivce;
    }

    @Override
    public List<GroupEntity> getEntities() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void save(final GroupEntity entity) {
        repository.save(entity);
        eventSerivce.dispatch(new GroupsChangeEvent());
    }
}
