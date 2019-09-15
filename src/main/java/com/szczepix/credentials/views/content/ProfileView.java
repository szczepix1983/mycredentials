package com.szczepix.credentials.views.content;

import com.szczepix.credentials.entities.ProfileEntity;
import com.szczepix.credentials.services.profileService.IProfileService;
import com.szczepix.credentials.views.FXMLView;
import com.szczepix.credentials.views.popups.InfoPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ProfileView extends FXMLView {

    @FXML
    public Button saveButton;
    @FXML
    public TextField totalText;
    @FXML
    public TextField firstNameText;
    @FXML
    public TextField lastNameText;
    @FXML
    public TextField usernameText;
    @FXML
    public TextField emailText;
    @FXML
    public TextField phoneText;
    @FXML
    public TextField passwordText;
    @FXML
    public TextField repeatPasswordText;

    @Autowired
    private IProfileService profileService;

    private ProfileEntity entity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entity = profileService.getEntity();

        enableButton(saveButton, this::onSaveButton);
        enableCompononet(firstNameText);
        enableCompononet(lastNameText);
        enableCompononet(usernameText);
        enableCompononet(emailText);
        enableCompononet(phoneText);
        enableCompononet(passwordText);
        enableCompononet(repeatPasswordText);

        firstNameText.setText(entity.getFirstName());
        lastNameText.setText(entity.getLastName());
        usernameText.setText(entity.getUserName());
        emailText.setText(entity.getEmail());
        phoneText.setText(entity.getMobile());
        totalText.setText(getTotalProfiles());
    }

    @Override
    public void destroy(){

    }

    private void onSaveButton(ActionEvent event) {
        String popupInfoMessage;
        try {
            popupInfoMessage = "Your profile password does not match. \n \n Password fields must be the same, provide correct password and try again.";
            if (validateForm()) {
                entity.setUserName(usernameText.getText());
                entity.setFirstName(firstNameText.getText());
                entity.setLastName(lastNameText.getText());
                entity.setEmail(emailText.getText());
                entity.setMobile(phoneText.getText());
                entity.setPassword(passwordText.getText());
                profileService.save(entity);
                popupInfoMessage = "Your profile has been successfully updated.";
            }
        } catch (Exception e) {
            popupInfoMessage = "Unexpected Error. \n \n " + e;
        }
        new InfoPopup(stageManager, popupInfoMessage);
    }

    private boolean validateForm() {
        return !passwordText.getText().isEmpty() && passwordText.getText().equals(repeatPasswordText.getText());
    }

    private String getTotalProfiles() {
        return "" + profileService.getEntities().size();
    }
}

