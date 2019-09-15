package com.szczepix.credentials.views.content;

import com.szczepix.credentials.enums.ContentViewType;
import com.szczepix.credentials.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuView extends FXMLView {

    @FXML
    public Button profileButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button groupsButton;
    @FXML
    public Button loginsButton;
    @FXML
    public Button licenseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(profileButton, this::handleProfileButton);
        enableButton(groupsButton, this::handleGroupsButton);
        enableButton(loginsButton, this::handleLoginsButton);
        enableButton(settingsButton, this::handleSettingsButton);
        enableButton(licenseButton, this::handleOpenLicense);
    }

    @Override
    public void destroy(){

    }

    protected void handleProfileButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.PROFILE, stageManager.getView().contentPane);
    }

    protected void handleGroupsButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.GROUPS, stageManager.getView().contentPane);
    }

    protected void handleLoginsButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.LOGINS, stageManager.getView().contentPane);
    }

    protected void handleSettingsButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.SETTINGS, stageManager.getView().contentPane);
    }

    protected void handleOpenLicense(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/szczepix1983/MyCredentials/blob/master/LICENSE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
