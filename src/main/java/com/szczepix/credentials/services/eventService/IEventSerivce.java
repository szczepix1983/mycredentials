package com.szczepix.credentials.services.eventService;

import com.szczepix.credentials.enums.BaseEventType;

import java.util.function.Consumer;

public interface IEventSerivce {

    void addListener(final BaseEventType eventType, final Consumer<BaseEvent> eventHandler);

    void removeListener(final BaseEventType eventType, final Consumer<BaseEvent> eventHandler);

    void dispatch(final BaseEvent event);
}
