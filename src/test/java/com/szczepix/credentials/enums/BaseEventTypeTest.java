package com.szczepix.credentials.enums;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseEventTypeTest {

    @Test
    public void getName() {
        BaseEventType[] types = BaseEventType.class.getEnumConstants();
        for (BaseEventType type : types) {
            assertThat(type.getName()).isNotNull();
            assertThat(type.getName()).isNotEmpty();
        }
    }

}