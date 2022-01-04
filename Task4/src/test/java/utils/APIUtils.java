package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import models.apiModels.Post;
import models.apiModels.userModel.User;
import utils.enums.Endpoints;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIUtils {

    private final static ISettingsFile settingsData = new JsonSettingsFile("settingsData.json");

    public APIUtils() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(settingsData.getValue("/baseUri").toString())
                .setContentType(ContentType.JSON)
                .build();
    }

    public List<Post> getPostList() {
        return given()
                .basePath(Endpoints.POSTS.getValue())
                .get()
                .then().spec(ResponceSpec.OK)
                .extract().jsonPath().getList("", Post.class);
    }

    public Post getPost(int postId) {
        return given()
                .basePath(Endpoints.POSTS.getValue())
                .get(String.valueOf(postId))
                .then().spec(ResponceSpec.OK)
                .extract().as(Post.class);
    }

    public Post getNotValidPostAsString(int postId) {
        return given()
                .basePath(Endpoints.POSTS.getValue())
                .get(String.valueOf(postId))
                .then().spec(ResponceSpec.NOT_FOUND)
                .extract().as(Post.class);
    }

    public Post getPostedPost(Post postRequest) {
        return given()
                .basePath(Endpoints.POSTS.getValue())
                .body(postRequest)
                .when().post()
                .then().spec(ResponceSpec.CREATE)
                .extract().as(Post.class);
    }

    public List<User> getUserList() {
        return given()
                .basePath(Endpoints.USERS.getValue())
                .get()
                .then().spec(ResponceSpec.OK)
                .extract().jsonPath().getList("", User.class);
    }

    public User getUserFromList(List<User> userList, int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }
        throw new IllegalArgumentException("UserNotFound");
    }

    public User getUser(int userId) {
        return given()
                .basePath(Endpoints.USERS.getValue())
                .get(String.valueOf(userId))
                .then().spec(ResponceSpec.OK)
                .extract().as(User.class);
    }
}