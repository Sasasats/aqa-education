package utils.enums;

import lombok.Getter;

public enum Endpoints {
    CREATE_TEST_POST("test/put"),
    GENERATE_TOKEN("token/get"),
    GET_LIST_TESTS_JSON("test/get/json"),
    SEND_TEST_ATTACHMENT("test/put/attachment"),
    SEND_TEST_LOG("test/put/log");

    @Getter
    private final String value;

    Endpoints(String value) {
        this.value = value;
    }
}