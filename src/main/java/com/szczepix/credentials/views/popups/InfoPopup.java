package com.szczepix.credentials.views.popups;

import com.szczepix.credentials.enums.PopupViewType;
import com.szczepix.credentials.managers.IStageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InfoPopup extends BasePopupView {

    @FXML
    public Button closeButton;
    @FXML
    public Label titleLabel;
    @FXML
    public Text messageText;

    private final String message;

    public InfoPopup(final IStageManager stageManager, final String message) {
        super(stageManager, PopupViewType.INFO);
        this.message = message;
    }

    @Override
    public void onInitalize() {
        enableButton(closeButton, this::onCloseButton);
        titleLabel.setText(PopupViewType.INFO.getTitle());
        messageText.setText(message);
    }

    private void onCloseButton(ActionEvent event) {
        close();
    }
}
