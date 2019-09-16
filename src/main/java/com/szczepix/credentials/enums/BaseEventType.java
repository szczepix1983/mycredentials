package com.szczepix.credentials.enums;

import lombok.Getter;

@Getter
public enum BaseEventType {

    MOCK("TEST_EVENT"),
    VIEW_CHANGE("VIEW_CHANGE"),
    GROUPS_CHANGE("GROUPS_CHANGE"),
    LOGINS_CHANGE("LOGINS_CHANGE"),
    ACCOUNTS_CHANGE("ACCOUNTS_CHANGE");

    String name;

    BaseEventType(final String name) {
        this.name = name;
    }
}
