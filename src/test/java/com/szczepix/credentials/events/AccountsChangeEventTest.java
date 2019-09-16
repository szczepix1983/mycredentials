package com.szczepix.credentials.events;

import com.szczepix.credentials.enums.BaseEventType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AccountsChangeEventTest {

    private AccountsChangeEvent event;

    @Before
    public void setUp() {
        event = new AccountsChangeEvent();
    }

    @Test
    public void creation() {
        assertThat(event).isNotNull();
    }

    @Test
    public void getEventType() {
        assertThat(event.getEventType()).isEqualByComparingTo(BaseEventType.ACCOUNTS_CHANGE);
    }
}