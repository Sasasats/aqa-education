package utils.enums;

import lombok.Getter;

public enum ColumnNames {
    TEST_NAME("Test name"),
    TEST_METHOD("Test method"),
    LATEST_TEST_RESULT("Latest test result"),
    LATEST_TEST_START_TIME("Latest test start time"),
    LATEST_TEST_END_TIME("Latest test end time"),
    LATEST_TEST_DURATION("Latest test duration (H:m:s.S)");

    @Getter
    private final String value;

    ColumnNames(String value) {
        this.value = value;
    }
}