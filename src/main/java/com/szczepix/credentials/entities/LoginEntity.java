package com.szczepix.credentials.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "logins")
@Data
public class LoginEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String login;

    @OneToMany(fetch = FetchType.EAGER)
    private List<GroupEntity> groups;


    @Override
    public boolean equals(Object obj) {
        LoginEntity entity = (LoginEntity) obj;
        return super.equals(obj) || (getId() == entity.getId() && login.equals(entity.getLogin()));
    }

    @Override
    public String toString() {
        return print(id, login);
    }
}
