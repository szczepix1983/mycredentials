package com.szczepix.credentials.views.content;

import com.szczepix.credentials.entities.AccountEntity;
import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.accountService.IAccountService;
import com.szczepix.credentials.services.eventService.BaseEvent;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import com.szczepix.credentials.services.loginService.ILoginService;
import com.szczepix.credentials.views.FXMLView;
import com.szczepix.credentials.views.components.AccountTableItem;
import com.szczepix.credentials.views.components.GroupTableItem;
import com.szczepix.credentials.views.popups.CreateAccountPopup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class AccountsView extends FXMLView {

    @FXML
    public TextField totalText;
    @FXML
    public Button createButton;
    @FXML
    public TableView<AccountTableItem> table;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    @Qualifier("eventService")
    private IEventSerivce eventSerivce;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);

        createColumns(Arrays.asList("name", "password", "website"));

        eventSerivce.addListener(BaseEventType.ACCOUNTS_CHANGE, this::onLoginsChange);

        fillContent();
    }

    @Override
    public void destroy() {
        eventSerivce.removeListener(BaseEventType.ACCOUNTS_CHANGE, this::onLoginsChange);
    }

    public void onLoginsChange(BaseEvent baseEvent) {
        fillContent();
    }

    private void fillContent() {
        totalText.setText(getTotal());

        List<AccountEntity> entities = accountService.getEntities();
        final ObservableList<AccountTableItem> data = entities.stream()
                .map(AccountTableItem::create)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        table.setItems(data);
    }

    private void createColumns(final List<String> columnNames){
        columnNames.stream()
                .map(name -> {
                    TableColumn column = new TableColumn(name);
                    column.setMinWidth(100);
                    column.setCellValueFactory(new PropertyValueFactory<GroupTableItem, String>(name));
                    return column;
                }).forEach(table.getColumns()::add);
    }

    private void onCreateButton(ActionEvent event) {
        new CreateAccountPopup(stageManager, loginService, accountService);
    }

    private String getTotal() {
        return "" + accountService.getEntities().size();
    }
}
