package com.szczepix.credentials.views.popups;

import com.szczepix.credentials.enums.PopupViewType;
import com.szczepix.credentials.managers.IStageManager;
import com.szczepix.credentials.services.groupService.IGroupService;
import com.szczepix.credentials.utils.Utils;
import com.szczepix.credentials.views.components.GroupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreateGroupPopup extends BasePopupView {

    @FXML
    public Button closeButton;
    @FXML
    public Label titleLabel;
    @FXML
    public TextField nameText;
    @FXML
    public Button saveButton;
    @FXML
    public AnchorPane container;
    @FXML
    public ColorPicker colorPicker;

    private final IGroupService groupService;

    private GroupComponent groupComponent;

    public CreateGroupPopup(final IStageManager stageManager, final IGroupService groupService) {
        super(stageManager, PopupViewType.CREATE_GROUP);
        this.groupService = groupService;
    }

    @Override
    public void onInitalize() {
        enableButton(closeButton, this::onCloseButton);
        enableButton(saveButton, this::onSaveButton);

        titleLabel.setText(PopupViewType.CREATE_GROUP.getTitle());

        groupComponent = new GroupComponent();
        container.getChildren().add(groupComponent);

        nameText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() < Utils.GROUP_NAME_MAX_LENGHT){
                groupComponent.setText(newValue);
            } else {
                nameText.setText(oldValue);
            }
        });

        colorPicker.setOnAction(this::onColorPickerChange);
    }

    private void onColorPickerChange(ActionEvent event){
        groupComponent.setColor(colorPicker.getValue());
    }

    private void onCloseButton(ActionEvent event) {
        close();
    }

    private void onSaveButton(ActionEvent event) {
        if(groupComponent.isValid()){
            groupService.save(groupComponent.getEntity());
            close();
            new InfoPopup(stageManager, "New group with name #"+ groupComponent.getEntity().getName() +" has been added properly.");
        } else {
            new InfoPopup(stageManager, "The name for a group is invalid.");
        }
    }
}
