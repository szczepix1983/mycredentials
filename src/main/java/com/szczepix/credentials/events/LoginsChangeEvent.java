package com.szczepix.credentials.events;

import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.eventService.BaseEvent;

public class LoginsChangeEvent extends BaseEvent {

    public LoginsChangeEvent(){
        super(BaseEventType.LOGINS_CHANGE);
    }
}

