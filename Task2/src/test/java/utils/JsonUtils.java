package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Post;
import models.userModel.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    private static final String TEST_POSTS_PATH = "post%s.json";
    private static final String TEST_USERS_PATH = "user%s.json";
    private static final String RESOURCE_PATH = "src/test/resources/";

    public static Post setPost(int postId) {
        String jsonString = getJsonString(String.format((RESOURCE_PATH + TEST_POSTS_PATH), postId));
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(reader, Post.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Не получилось записать значения");
        }
    }

    public static User setUser(int postId) {
        String jsonString = getJsonString(String.format((RESOURCE_PATH + TEST_USERS_PATH), postId));
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(reader, User.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Не получилось записать значения");
        }
    }

    private static String getJsonString(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Не удалось получить строку");
        }
    }
}