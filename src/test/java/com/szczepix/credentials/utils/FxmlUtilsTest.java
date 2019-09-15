package com.szczepix.credentials.utils;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class FxmlUtilsTest {

    private JFXPanel fxPanel;

    @Before
    public void setUp() {
        fxPanel = new JFXPanel();
    }

    @Test
    public void load() throws Exception {
        AnnotationConfigApplicationContext context = mock(AnnotationConfigApplicationContext.class);
        URL url = getClass().getClassLoader().getResource("mock.fxml");
        FxmlUtils.FXMLDescriptor descriptor = FxmlUtils.load(url, context);

        assertThat(descriptor).isNotNull();
        assertThat(descriptor.getParent()).isNotNull();
    }

    @Test
    public void loadComponent() {
        try {
            URL url = getClass().getClassLoader().getResource("mockItem.fxml");
            FxmlUtils.load(url, new AnchorPane());
        } catch (Exception e) {
            fail("Unexpected exception has been thrown");
        }
    }

    @Test(expected = Exception.class)
    public void loadComponentWithException() throws Exception {
        URL url = getClass().getClassLoader().getResource("x.fxml");
        FxmlUtils.load(url, new AnchorPane());
    }

}