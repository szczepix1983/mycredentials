package com.szczepix.credentials.views.popups;

import com.szczepix.credentials.entities.GroupEntity;
import com.szczepix.credentials.enums.PopupViewType;
import com.szczepix.credentials.managers.IStageManager;
import com.szczepix.credentials.services.groupService.IGroupService;
import com.szczepix.credentials.services.loginService.ILoginService;
import com.szczepix.credentials.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.stream.Collectors;

public class CreateLoginPopup extends BasePopupView {

    @FXML
    public Button closeButton;
    @FXML
    public Label titleLabel;
    @FXML
    public TextField emailText;
    @FXML
    public Button saveButton;
    @FXML
    public AnchorPane container;
    @FXML
    public ListView<String> listView;

    private final ILoginService loginService;

    private final IGroupService groupService;

    public CreateLoginPopup(final IStageManager stageManager, final ILoginService loginService, final IGroupService groupService) {
        super(stageManager, PopupViewType.CREATE_LOGIN);
        this.loginService = loginService;
        this.groupService = groupService;
    }

    @Override
    public void onInitalize() {
        enableButton(closeButton, this::onCloseButton);
        enableButton(saveButton, this::onSaveButton);

        titleLabel.setText(PopupViewType.CREATE_LOGIN.getTitle());

        emailText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() < Utils.GROUP_NAME_MAX_LENGHT){

            } else {
                emailText.setText(oldValue);
            }
        });

        ObservableList<String> list = groupService.getEntities().stream().map(GroupEntity::getName).collect(Collectors.toCollection(FXCollections::observableArrayList));

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setItems(list);
        listView.setOnMouseClicked(this::onSelectionChange);
    }

    private void onSelectionChange(MouseEvent event) {
        ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();

        for(String s : selectedItems){
            System.out.println("selected item " + s);
        }

    }

    private void onCloseButton(ActionEvent event) {
        close();
    }

    private void onSaveButton(ActionEvent event) {
//        if(groupComponent.isValid()){
//            loginService.save();
//            close();
//            new InfoPopup(stageManager, "New group with name #"+ groupComponent.getEntity().getName() +" has been added properly.");
//        } else {
//            new InfoPopup(stageManager, "The name for a group is invalid.");
//        }
    }
}
