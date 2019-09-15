package com.szczepix.credentials.views.content;

import com.szczepix.credentials.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SettingsView extends FXMLView {

    @FXML
    public Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(saveButton, this::onSaveButton);
    }

    @Override
    public void destroy(){

    }

    private void onSaveButton(ActionEvent event) {
        String popupInfoMessage;
        try {
//            settingService.getSettings().getEntity().setResourceSyncApi(resourceApiCombobox.getValue().getUrl());
//            settingService.getSettings().getEntity().setCurrency(currencyCombobox.getValue());
//            settingService.getSettings().getEntity().setResourceSyncInterval(Integer.parseInt(resourceIntervalText.getText()));
//            settingService.save();

            popupInfoMessage = "The settings has been successfully updated.";
        } catch (Exception e) {
            popupInfoMessage = "Unexpected Error. \n \n " + e;
        }
//        new InfoPopup(stageManager, popupInfoMessage);
    }
}

