package com.szczepix.credentials.views.components;

import com.szczepix.credentials.entities.AccountEntity;
import javafx.beans.property.SimpleStringProperty;

public class AccountTableItem {
    private final SimpleStringProperty name;
    private final SimpleStringProperty password;
    private final SimpleStringProperty website;

    public static AccountTableItem create(final AccountEntity entity){
        return new AccountTableItem(entity.getName(), entity.getPassword(), entity.getWebsite());
    }

    private AccountTableItem(final String name, final String password, final String website) {
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.website = new SimpleStringProperty(website);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }

    public String getPassword() {
        return password.get();
    }
    public void setPassword(String fName) {
        password.set(fName);
    }

    public String getWebsite() {
        return website.get();
    }
    public void setWebsite(String fName) {
        website.set(fName);
    }
}
