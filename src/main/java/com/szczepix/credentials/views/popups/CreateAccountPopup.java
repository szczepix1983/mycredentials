package com.szczepix.credentials.views.popups;

import com.szczepix.credentials.entities.LoginEntity;
import com.szczepix.credentials.enums.PopupViewType;
import com.szczepix.credentials.managers.IStageManager;
import com.szczepix.credentials.services.accountService.IAccountService;
import com.szczepix.credentials.services.loginService.ILoginService;
import com.szczepix.credentials.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.stream.Collectors;

public class CreateAccountPopup extends BasePopupView {

    @FXML
    public Button closeButton;
    @FXML
    public Label titleLabel;
    @FXML
    public TextField nameText;
    @FXML
    public TextField websiteText;
    @FXML
    public TextField passwordText;
    @FXML
    public Button saveButton;
    @FXML
    public ComboBox loginCombobox;

    private final ILoginService loginService;

    private final IAccountService accountService;

    private HashMap<String, LoginEntity> loginNameToEntity = new HashMap<>();

    public CreateAccountPopup(final IStageManager stageManager, final ILoginService loginService, final IAccountService accountService) {
        super(stageManager, PopupViewType.CREATE_ACCOUNT);
        this.loginService = loginService;
        this.accountService = accountService;
    }

    @Override
    public void onInitalize() {
        enableButton(closeButton, this::onCloseButton);
        enableButton(saveButton, this::onSaveButton);

        titleLabel.setText(PopupViewType.CREATE_ACCOUNT.getTitle());

        nameText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() < Utils.LOGIN_NAME_MAX_LENGHT){
//                loginComponent.setText(newValue);
            } else {
                nameText.setText(oldValue);
            }
        });

        ObservableList<String> list = loginService.getEntities().stream()
                .peek(entity -> loginNameToEntity.put(entity.getLogin(), entity))
                .map(LoginEntity::getLogin)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        loginCombobox.getItems().addAll(list);
        loginCombobox.setOnAction(this::onSelectionChange);
    }

    private void onSelectionChange(Event event) {

        System.out.println(loginCombobox.getValue());
    }

    private void onCloseButton(ActionEvent event) {
        close();
    }

    private void onSaveButton(ActionEvent event) {
//        if(loginComponent.isValid()){
//            loginService.save(loginComponent.getEntity());
//            close();
//            new InfoPopup(stageManager, "New account for "+ loginComponent.getEntity().getEmail() +" has been added properly.");
//        } else {
//            new InfoPopup(stageManager, "The login is invalid. Try to put some more letters");
//        }
    }
}
