package com.szczepix.credentials.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ContentViewTypeTest {

    @Test
    public void getTitle() {
        ContentViewType[] types = ContentViewType.class.getEnumConstants();
        for (ContentViewType type : types) {
            assertThat(type.getTitle()).isNotNull();
            assertThat(type.getTitle()).isNotEmpty();
        }
    }

    @Test
    public void getPath() {
        ContentViewType[] types = ContentViewType.class.getEnumConstants();
        for (ContentViewType type : types) {
            assertThat(type.getPath()).isNotNull();
            assertThat(type.getPath()).isNotEmpty();
        }
    }

}