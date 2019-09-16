package com.szczepix.credentials.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String website;

    private String password;

    @ManyToOne
    private LoginEntity login;

    @Override
    public boolean equals(Object obj) {
        AccountEntity entity = (AccountEntity) obj;
        return super.equals(obj) || (getId() == entity.getId() && password.equals(entity.getPassword()));
    }

    @Override
    public String toString() {
        return print(id);
    }
}
