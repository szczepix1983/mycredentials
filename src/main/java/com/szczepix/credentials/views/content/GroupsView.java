package com.szczepix.credentials.views.content;

import com.szczepix.credentials.entities.GroupEntity;
import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.eventService.BaseEvent;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import com.szczepix.credentials.services.groupService.IGroupService;
import com.szczepix.credentials.views.FXMLView;
import com.szczepix.credentials.views.components.GroupTableItem;
import com.szczepix.credentials.views.popups.CreateGroupPopup;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class GroupsView extends FXMLView {

    @FXML
    public TextField totalText;
    @FXML
    public Button createButton;
    @FXML
    public TableView<GroupTableItem> table;

    @Autowired
    private IGroupService groupService;

    @Autowired
    @Qualifier("eventService")
    private IEventSerivce eventSerivce;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<GroupTableItem, String>("name"));

        TableColumn colorCol = new TableColumn("Color");
        colorCol.setMinWidth(100);
        colorCol.setCellValueFactory(
                new PropertyValueFactory<GroupTableItem, String>("color"));


        table.getColumns().addAll(nameCol, colorCol);

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

        List<GroupEntity> entities = groupService.getEntities();
        final ObservableList<GroupTableItem> data = entities.stream()
                .map(GroupTableItem::create)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        table.setItems(data);
    }

    private void onCreateButton(ActionEvent event) {
        new CreateGroupPopup(stageManager, groupService);
    }

    private String getTotal() {
        return "" + groupService.getEntities().size();
    }
}
