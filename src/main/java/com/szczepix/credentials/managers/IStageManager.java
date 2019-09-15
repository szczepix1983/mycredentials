package com.szczepix.credentials.managers;

import com.szczepix.credentials.enums.AppViewType;
import com.szczepix.credentials.enums.ContentViewType;
import com.szczepix.credentials.views.MainView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface IStageManager {

    Stage getStage();

    MainView getView();

    void setView(MainView mainView);

    void show(final AppViewType view);

    void show(final ContentViewType contentViewType, final Pane pane);
}
