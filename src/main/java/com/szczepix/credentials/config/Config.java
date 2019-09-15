package com.szczepix.credentials.config;

import com.szczepix.credentials.managers.IStageManager;
import com.szczepix.credentials.managers.StageManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@ComponentScan("com.szczepix.credentials")
@PropertySource("classpath:application.properties")
@Configuration
public class Config {

    @Autowired
    private Environment env;

    public String getValue(final String valueName) {
        return env.getProperty(valueName);
    }

    @Bean
    @Lazy
    public IStageManager stageManager(final Stage stage) {
        IStageManager stageManager = new StageManager(stage);
        return stageManager;
    }
}
