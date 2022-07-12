package utils.enums;

import lombok.Getter;

public enum UsersParams {
    VALID_USER_ID(5);

    @Getter
    private final Integer value;

    UsersParams(Integer value) {
        this.value = value;
    }
}