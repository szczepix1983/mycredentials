package com.szczepix.credentials.services.profileService;

import com.szczepix.credentials.dao.IProfileRepository;
import com.szczepix.credentials.entities.ProfileEntity;
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
public class ProfileService implements IProfileService {

    private final IProfileRepository repository;

    private final IEventSerivce eventService;

    @Autowired
    public ProfileService(final IProfileRepository repository, @Qualifier("eventService") final IEventSerivce eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

    @Override
    public ProfileEntity findProfileByUserNameAndPassword(String username, String password) {
        return repository.findProfileByUserNameAndPassword(username, password);
    }

    @Override
    public List<ProfileEntity> getEntities() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileEntity getEntity() {
        if (!getEntities().isEmpty()) {
            return getEntities().get(0);
        }
        return createNewProfile();
    }

    @Override
    public void save(final ProfileEntity entity) {
        repository.save(entity);
    }

    private ProfileEntity createNewProfile() {
        final ProfileEntity entity = new ProfileEntity();
        entity.setUserName("");
        entity.setFirstName("");
        entity.setLastName("");
        entity.setEmail("");
        entity.setMobile("");
        entity.setPassword("");
        return entity;
    }
}
