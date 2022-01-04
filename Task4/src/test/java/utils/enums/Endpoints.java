package utils.enums;

import lombok.Getter;

public enum Endpoints {
    POSTS("/posts/"),
    USERS("/users/");

    @Getter
    private final String value;

    Endpoints(String value) {
        this.value = value;
    }
}