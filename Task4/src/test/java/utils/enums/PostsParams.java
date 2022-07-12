package utils.enums;

import lombok.Getter;

public enum PostsParams {
    VALID_POST_ID(99),
    NOT_VALID_POST_ID(150);

    @Getter
    private final Integer value;

    PostsParams(Integer value) {
        this.value = value;
    }
}