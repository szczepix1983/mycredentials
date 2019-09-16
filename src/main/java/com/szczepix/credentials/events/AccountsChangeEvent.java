package com.szczepix.credentials.events;

import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.eventService.BaseEvent;

public class AccountsChangeEvent extends BaseEvent {

    public AccountsChangeEvent(){
        super(BaseEventType.ACCOUNTS_CHANGE);
    }
}

