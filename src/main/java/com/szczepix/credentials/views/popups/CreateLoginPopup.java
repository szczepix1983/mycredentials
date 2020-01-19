package com.szczepix.credentials.views.popups;

import com.szczepix.credentials.entities.GroupEntity;
import com.szczepix.credentials.enums.PopupViewType;
import com.szczepix.credentials.managers.IStageManager;
import com.szczepix.credentials.services.groupService.IGroupService;
import com.szczepix.credentials.services.loginService.ILoginService;
import com.szczepix.credentials.utils.Utils;
import com.szczepix.credentials.views.components.LoginComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
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

    private LoginComponent loginComponent;

    private HashMap<String, GroupEntity> groupNameToEntity = new HashMap<>();

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
            if(newValue.length() < Utils.LOGIN_NAME_MAX_LENGHT){
                loginComponent.setText(newValue);
            } else {
                emailText.setText(oldValue);
            }
        });

        ObservableList<String> list = groupService.getEntities().stream()
                .peek(entity -> groupNameToEntity.put(entity.getName(), entity))
                .map(GroupEntity::getName)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setItems(list);
        listView.setOnMouseClicked(this::onSelectionChange);

        loginComponent = new LoginComponent();
        container.getChildren().add(loginComponent);
    }

    private void onSelectionChange(MouseEvent event) {
        ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();

        loginComponent.setGroups(
                selectedItems.stream()
                        .map(item -> groupNameToEntity.get(item))
                        .collect(Collectors.toList())
        );
    }

    private void onCloseButton(ActionEvent event) {
        close();
    }

    private void onSaveButton(ActionEvent event) {
        if(loginComponent.isValid()){
            loginService.save(loginComponent.getEntity());
            close();
            new InfoPopup(stageManager, "New login with name #"+ loginComponent.getEntity().getLogin() +" has been added properly.");
        } else {
            new InfoPopup(stageManager, "The login is invalid. Try to put some more letters");
        }
    }
}
