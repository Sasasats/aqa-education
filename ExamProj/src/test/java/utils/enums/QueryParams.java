package utils.enums;

import lombok.Getter;

public enum QueryParams {
    BROWSER("browser"),
    CONTENT("content"),
    CONTENT_TYPE("contentType"),
    CONTENT_IMAGE_TYPE("image/png"),
    ENV("env"),
    METHOD_NAME("methodName"),
    PROJECT_ID("projectId"),
    PROJECT_NAME("projectName"),
    SID("SID"),
    START_TIME("startTime"),
    TEST_ID("testId"),
    TEST_NAME("testName"),
    VARIANT("variant");

    @Getter
    private final String value;

    QueryParams(String value) {
        this.value = value;
    }
}