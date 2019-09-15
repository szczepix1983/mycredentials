package com.szczepix.credentials.config;

import javafx.stage.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.AppConfigTestConfiguration.class)
@TestPropertySource(locations = {"classpath:application.properties"})
public class ConfigTest {

    @Autowired
    private Config config;

    @Test
    public void getValue() {
        assertThat(config.getValue("spring.datasource.url")).isNotNull();
        assertThat(config.getValue("spring.datasource.url")).isEqualTo("jdbc:sqlite:data.db");
    }

    @Test
    public void stageManager() {
        assertThat(config.stageManager(Mockito.mock(Stage.class))).isNotNull();
    }

    @Configuration
    static class AppConfigTestConfiguration {

        @Bean
        public Config config() {
            return new Config();
        }
    }
}