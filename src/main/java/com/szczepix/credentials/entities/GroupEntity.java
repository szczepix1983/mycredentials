package com.szczepix.credentials.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "groups")
@Data
public class GroupEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String color;

    @Override
    public boolean equals(Object obj) {
        GroupEntity entity = (GroupEntity) obj;
        return super.equals(obj) || (getId() == entity.getId() && name.equals(entity.name) && color.equals(entity.color));
    }

    @Override
    public String toString() {
        return print(id, name, color);
    }
}