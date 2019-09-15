package com.szczepix.credentials.managers;

import com.szczepix.credentials.enums.AppViewType;
import com.szczepix.credentials.enums.ContentViewType;
import com.szczepix.credentials.utils.FxmlUtils;
import com.szczepix.credentials.views.MainView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;
import java.util.logging.Logger;

public class StageManager implements IStageManager {

    private static final Logger LOG = Logger.getAnonymousLogger();
    @Getter
    private final Stage stage;
    @Autowired
    private AnnotationConfigApplicationContext context;
    @Getter
    @Setter
    private MainView view;

    private FxmlUtils.FXMLDescriptor activeFXML;

    public StageManager(final Stage stage) {
        this.stage = stage;
        this.stage.initStyle(StageStyle.DECORATED);
    }

    @Override
    public void show(final AppViewType view) {
        FxmlUtils.FXMLDescriptor viewRootNodeHierarchy = loadViewNodeHierarchy(view.getPath());
        Scene scene = prepareScene(viewRootNodeHierarchy.getParent());

        stage.setTitle(view.getTitle());
        stage.setResizable(view.isResizeable());
        try {
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        } catch (Exception exception) {
            LOG.warning("Unable to show scene for title" + view.getTitle());
        }
    }

    @Override
    public void show(final ContentViewType contentViewType, final Pane pane) {
        if(Objects.nonNull(activeFXML)){
            activeFXML.getController().destroy();
        }
        activeFXML = loadViewNodeHierarchy(contentViewType.getPath());
        pane.getChildren().clear();
        pane.getChildren().add(activeFXML.getParent());
    }

    private Scene prepareScene(final Parent rootnode) {
        Scene scene = getCurrentScene(rootnode);
        scene.setRoot(rootnode);
        return scene;
    }

    private Scene getCurrentScene(final Parent rootnode) {
        try {
            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(rootnode);
            }
            return scene;
        } catch (Exception e) {
            return new Scene(rootnode);
        }
    }

    private FxmlUtils.FXMLDescriptor loadViewNodeHierarchy(final String fxmlFilePath) {
        FxmlUtils.FXMLDescriptor rootNode = null;
        try {
            rootNode = FxmlUtils.load(getClass().getClassLoader().getResource(fxmlFilePath), context);
            Objects.requireNonNull(rootNode.getParent(), "A Root FXML node must not be null");
        } catch (Exception exception) {
            LOG.warning(exception.toString());
        }
        return rootNode;
    }
}
