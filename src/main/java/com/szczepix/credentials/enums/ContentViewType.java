package com.szczepix.credentials.enums;

import lombok.Getter;

@Getter
public enum ContentViewType {

    MENU("Menu", "menu.fxml"),
    PROFILE("Profile", "profile.fxml"),
    GROUPS("Groups", "groups.fxml"),
    LOGINS("Logins", "logins.fxml"),
    SETTINGS("Settings", "settings.fxml");

    String title;
    String path;

    ContentViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}

