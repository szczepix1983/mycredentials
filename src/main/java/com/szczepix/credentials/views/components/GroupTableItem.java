package com.szczepix.credentials.views.components;

import com.szczepix.credentials.entities.GroupEntity;
import javafx.beans.property.SimpleStringProperty;

public class GroupTableItem {
    private final SimpleStringProperty name;
    private final SimpleStringProperty color;

    public static GroupTableItem create(final GroupEntity entity){
        return new GroupTableItem(entity.getName(), entity.getColor());
    }

    private GroupTableItem(final String name, final String color) {
        this.name = new SimpleStringProperty(name);
        this.color = new SimpleStringProperty(color);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }

    public String getColor() {
        return color.get();
    }
    public void setColor(String fName) {
        color.set(fName);
    }

}
