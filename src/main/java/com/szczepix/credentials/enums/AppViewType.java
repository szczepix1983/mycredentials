package com.szczepix.credentials.enums;

import lombok.Getter;

@Getter
public enum AppViewType {

    MAIN("Welcome", "main.fxml", true),
    MOCK("Test", "mock.fxml", true);

    String title;
    String path;
    boolean resizeable;

    AppViewType(final String title, final String path, final boolean resizeable) {
        this.title = title;
        this.path = path;
        this.resizeable = resizeable;
    }
}
