package com.szczepix.credentials.events;

import com.szczepix.credentials.enums.BaseEventType;
import com.szczepix.credentials.services.eventService.BaseEvent;

public class GroupsChangeEvent extends BaseEvent {

    public GroupsChangeEvent(){
        super(BaseEventType.GROUPS_CHANGE);
    }
}

