package com.szczepix.credentials.services.profileService;

import com.szczepix.credentials.entities.ProfileEntity;

import java.util.List;

public interface IProfileService {

    ProfileEntity findProfileByUserNameAndPassword(String username, String password);

    List<ProfileEntity> getEntities();

    ProfileEntity getEntity();

    void save(final ProfileEntity entity);
}
