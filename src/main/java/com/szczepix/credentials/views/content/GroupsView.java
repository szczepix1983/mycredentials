package com.szczepix.credentials.views.content;

import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.eventService.BaseEvent;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import com.szczepix.credentials.services.groupService.IGroupService;
import com.szczepix.credentials.views.FXMLView;
import com.szczepix.credentials.views.popups.CreateGroupPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GroupsView extends FXMLView {

    @FXML
    public TextField totalText;
    @FXML
    public Button createButton;
    @FXML
    public GridPane gridPane;

    @Autowired
    private IGroupService groupService;

    @Autowired
    @Qualifier("eventService")
    private IEventSerivce eventSerivce;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);

        eventSerivce.addListener(BaseEventType.GROUPS_CHANGE, this::onGroupsChange);

        fillContent();
    }

    @Override
    public void destroy() {
        eventSerivce.removeListener(BaseEventType.GROUPS_CHANGE, this::onGroupsChange);
    }

    public void onGroupsChange(BaseEvent baseEvent) {
        fillContent();
    }

    private void fillContent() {
        totalText.setText(getTotal());

//        List<WalletModel> wallets = walletService.getWallets();
//        for (int i = 0; i < wallets.size(); i++) {
//            WalletModel walletModel = wallets.get(i);
//            try {
//                gridPane.add(new WalletItemComponent(walletModel, eventService, settingService.getSettings().getEntity().getCurrency()).load(), 0, i);
//            } catch (Exception e) {
//                System.out.println("eeee: " + e);
//            }
//        }
    }

    private void onCreateButton(ActionEvent event) {
        new CreateGroupPopup(stageManager, groupService);
    }

    private String getTotal() {
        return "" + groupService.getEntities().size();
    }
}
