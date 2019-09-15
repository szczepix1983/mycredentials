package com.szczepix.credentials;

import com.szczepix.credentials.config.Config;
import com.szczepix.credentials.enums.AppViewType;
import com.szczepix.credentials.managers.IStageManager;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Config.class)
public class Application extends javafx.application.Application {

    protected ConfigurableApplicationContext context;

    protected IStageManager stageManager;

    public static void main(String[] args) {
        launch(Application.class, args);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(Application.class);
    }

    @Override
    public void start(Stage primaryStage) {
        stageManager = context.getBean(IStageManager.class, primaryStage);
        stageManager.show(AppViewType.MAIN);
    }

    @Override
    public void stop() {
        context.stop();
    }
}
