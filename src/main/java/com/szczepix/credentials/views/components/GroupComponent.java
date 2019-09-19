package com.szczepix.credentials.views.components;

import com.szczepix.credentials.entities.GroupEntity;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Objects;


public class GroupComponent extends VBox {

    private TextField textField;

    private final GroupEntity entity;

    public GroupComponent(){
        this(new GroupEntity());
    }

    public GroupComponent(final GroupEntity entity){
        this.textField = new TextField();
        this.textField.setLayoutX(0);
        this.textField.setEditable(false);

        this.entity = entity;

        this.getChildren().add(textField);

        setColor(Objects.nonNull(entity.getColor()) ? Color.valueOf(entity.getColor()) : Color.WHITE);
        setText(Objects.nonNull(entity.getName()) ? entity.getName() : "");
    }

    public void setText(final String value){
        this.textField.setText("#" + value);
        this.textField.setPrefWidth(18 + (this.textField.getText().length() * 9));
        entity.setName(value);
    }

    public void setColor(final Color value) {
        entity.setColor("#"+value.toString().replace("0x", ""));
        this.textField.setStyle("-fx-background-color: "+entity.getColor()+";-fx-text-fill:#ffffff");
    }

    public GroupEntity getEntity(){
        return entity;
    }

    public boolean isValid() {
        return !entity.getName().isEmpty();
    }
}
