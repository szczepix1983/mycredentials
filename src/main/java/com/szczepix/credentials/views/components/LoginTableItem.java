package com.szczepix.credentials.views.components;

import com.szczepix.credentials.entities.LoginEntity;
import javafx.beans.property.SimpleStringProperty;

public class LoginTableItem {
    private final SimpleStringProperty login;

    public static LoginTableItem create(final LoginEntity entity){
        return new LoginTableItem(entity.getLogin());
    }

    private LoginTableItem(final String login) {
        this.login = new SimpleStringProperty(login);
    }

    public String getLogin() {
        return login.get();
    }
    public void setLogin(String value) {
        login.set(value);
    }

}
