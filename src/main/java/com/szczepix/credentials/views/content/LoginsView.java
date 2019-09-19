package com.szczepix.credentials.views.content;

import com.szczepix.credentials.entities.LoginEntity;
import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.eventService.BaseEvent;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import com.szczepix.credentials.services.groupService.IGroupService;
import com.szczepix.credentials.services.loginService.ILoginService;
import com.szczepix.credentials.views.FXMLView;
import com.szczepix.credentials.views.components.LoginComponent;
import com.szczepix.credentials.views.popups.CreateLoginPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class LoginsView extends FXMLView {

    @FXML
    public TextField totalText;
    @FXML
    public Button createButton;
    @FXML
    public GridPane gridPane;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    @Qualifier("eventService")
    private IEventSerivce eventSerivce;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);

        eventSerivce.addListener(BaseEventType.LOGINS_CHANGE, this::onLoginsChange);

        fillContent();
    }

    @Override
    public void destroy() {
        eventSerivce.removeListener(BaseEventType.LOGINS_CHANGE, this::onLoginsChange);
    }

    public void onLoginsChange(BaseEvent baseEvent) {
        fillContent();
    }

    private void fillContent() {
        totalText.setText(getTotal());

        List<LoginEntity> entities = loginService.getEntities();
        for (int i = 0; i < entities.size(); i++) {
            LoginEntity loginEntity = entities.get(i);
            try {
                gridPane.add(new LoginComponent(loginEntity), 0, i);
            } catch (Exception e) {
                System.out.println("eeee: " + e);
            }
        }
    }

    private void onCreateButton(ActionEvent event) {
        new CreateLoginPopup(stageManager, loginService, groupService);
    }

    private String getTotal() {
        return "" + loginService.getEntities().size();
    }
}
