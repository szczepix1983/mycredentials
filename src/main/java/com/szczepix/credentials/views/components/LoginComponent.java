package com.szczepix.credentials.views.components;

import com.szczepix.credentials.entities.GroupEntity;
import com.szczepix.credentials.entities.LoginEntity;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginComponent extends VBox {

    private TextField textField;

    private final LoginEntity entity;

    private final HBox container = new HBox();

    public LoginComponent(){
        this(new LoginEntity());
    }

    public LoginComponent(final LoginEntity entity){
        this.textField = new TextField();
        this.textField.setLayoutX(0);
        this.textField.setEditable(false);

        this.entity = entity;

        this.getChildren().add(textField);
        this.getChildren().add(container);

        setText(Objects.nonNull(entity.getLogin()) ? entity.getLogin() : "");
        setGroups(Objects.nonNull(entity.getGroups()) ? entity.getGroups() : new ArrayList<>());
    }

    public void setText(final String value){
        this.textField.setText("#" + value);
        this.textField.setPrefWidth(18 + (this.textField.getText().length() * 9));
        entity.setLogin(value);
    }

    public void setGroups(final List<GroupEntity> groups) {
        entity.setGroups(groups);
        container.getChildren().clear();
        for (GroupEntity group : groups) {
            Circle circle = new Circle(0, 0 , 5, Color.valueOf(group.getColor()));
            circle.setStrokeWidth(1);
            circle.setStroke(Color.WHITE);
            container.getChildren().add(circle);
        }
    }

    public LoginEntity getEntity(){
        return entity;
    }

    public boolean isValid() {
        return !entity.getLogin().isEmpty();
    }
}
