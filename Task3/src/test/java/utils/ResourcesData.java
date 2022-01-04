package utils;

import lombok.Getter;

public enum ResourcesData {
    PATH_TO_PHOTO("src/test/resources/photo.jpg");

    @Getter
    private final String value;

    ResourcesData(String value) {
        this.value = value;
    }
}