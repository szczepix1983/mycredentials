package com.szczepix.credentials.utils;

import com.szczepix.credentials.views.FXMLView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;

public class FxmlUtils {

    private FxmlUtils() {}

    public static FXMLDescriptor load(final URL path, final AnnotationConfigApplicationContext context) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setLocation(path);
        return new FXMLDescriptor(loader.load(), loader.getController());
    }

    public static void load(final URL path, final Object controller) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        fxmlLoader.setRoot(controller);
        fxmlLoader.setLocation(path);
        fxmlLoader.load();
    }

    @Getter
    public static class FXMLDescriptor{

        private final Parent parent;
        private final FXMLView controller;

        public FXMLDescriptor(final Parent parent, final FXMLView controller) {
            this.controller = controller;
            this.parent = parent;
        }
    }
}