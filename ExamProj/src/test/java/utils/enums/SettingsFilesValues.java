package utils.enums;

import lombok.Getter;

public enum SettingsFilesValues {
    BASE_URI_WEB("/baseUriWeb"),
    BASE_URI_API("/baseUriApi"),
    BROWSER_NAME("/browserName"),
    LOGIN("/login"),
    METHOD_NAME("/methodName"),
    PASSWORD("/password"),
    PROJECT_NAME("/projectName"),
    TEST_NAME("/testName"),
    VARIANT("/variant");

    @Getter
    private final String value;

    SettingsFilesValues(String value) {
        this.value = value;
    }
}