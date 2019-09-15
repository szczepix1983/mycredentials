package com.szczepix.credentials.models;

import com.szczepix.credentials.entities.SettingEntity;
import lombok.Getter;

@Getter
public class SettingModel {

    private final SettingEntity entity;

    public SettingModel(final SettingEntity entity) {
        this.entity = entity;
    }
}
