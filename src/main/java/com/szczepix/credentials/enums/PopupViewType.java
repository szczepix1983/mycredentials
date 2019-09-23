package com.szczepix.credentials.enums;

import lombok.Getter;

@Getter
public enum PopupViewType {

    EXCEPTION("Exception", "exception.fxml"),
    INFO("Information", "popups/infoPopup.fxml"),
    CREATE_GROUP("Create group", "popups/createGroupPopup.fxml"),
    CREATE_LOGIN("Create login", "popups/createLoginPopup.fxml"),
    CREATE_ACCOUNT("Create account", "popups/createAccountPopup.fxml"),
    MOCK("Test", "mock.fxml");

    String title;
    String path;

    PopupViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}
