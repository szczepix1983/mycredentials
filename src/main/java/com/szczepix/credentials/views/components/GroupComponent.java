package com.szczepix.credentials.views.components;

import com.szczepix.credentials.entities.GroupEntity;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


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

        setText("");
        setColor(Color.WHITE);
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
