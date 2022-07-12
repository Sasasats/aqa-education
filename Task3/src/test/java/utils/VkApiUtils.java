package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.http.ContentType;
import models.*;
import models.savePhoto.SavedPhoto;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class VkApiUtils {
    private static final ISettingsFile USER_DATA = new JsonSettingsFile("userAuthorizationData.json");

    private static final String PATTERN_UP_REQ = "%s%s_%s";

    public static Entry postEntry(String message) {
        return given().
                queryParam(RequestParams.MESSAGE.getValue(), message).
                when().post(Endpoints.POST.getValue()).
                then().statusCode(200).
                extract().jsonPath().getObject(RequestParams.RESPONSE.getValue(), Entry.class);
    }

    public static UploadLink getUploadLink() {
        return given().
                queryParam(RequestParams.USER_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                when().post(Endpoints.UPLOAD_PHOTO.getValue()).
                then().statusCode(200).
                extract().jsonPath().getObject(RequestParams.RESPONSE.getValue(), UploadLink.class);
    }

    public static UploadedPhoto uploadPhoto(UploadLink uploadLink, String pathToPhoto) {
        return given().
                contentType(ContentType.MULTIPART).
                multiPart(new File(pathToPhoto)).
                when().post(uploadLink.getUpload_url()).
                then().statusCode(200).
                extract().jsonPath().getObject(RequestParams.NOTHING.getValue(), UploadedPhoto.class);
    }

    public static List<SavedPhoto> savePhoto(UploadedPhoto uploadedPhoto) {
        return given().
                queryParam(RequestParams.ITEM_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                queryParam(RequestParams.PHOTO.getValue(), uploadedPhoto.getPhoto()).
                queryParam(RequestParams.SERVER.getValue(), String.valueOf(uploadedPhoto.getServer())).
                queryParam(RequestParams.HASH.getValue(), uploadedPhoto.getHash()).
                when().post(Endpoints.SAVE_PHOTO.getValue()).
                then().statusCode(200).
                extract().jsonPath().getList(RequestParams.RESPONSE.getValue(), SavedPhoto.class);
    }

    public static Entry updateEntry(Entry postedEntry, String message, SavedPhoto savedPhoto) {
        return given().
                queryParam(RequestParams.OWNER_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                queryParam(RequestParams.POST_ID.getValue(), postedEntry.getPost_id()).
                queryParam(RequestParams.MESSAGE.getValue(), message).
                queryParam(RequestParams.ATTACHMENTS.getValue(),
                        String.format(PATTERN_UP_REQ, RequestParams.PHOTO.getValue(), savedPhoto.getOwner_id(),
                                savedPhoto.getId())).
                when().post(Endpoints.EDIT.getValue()).
                then().statusCode(200).
                extract().jsonPath().getObject(RequestParams.RESPONSE.getValue(), Entry.class);
    }

    public static Comment createComment(Entry entry, String message) {
        return given().
                queryParam(RequestParams.OWNER_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                queryParam(RequestParams.POST_ID.getValue(), entry.getPost_id()).
                queryParam(RequestParams.MESSAGE.getValue(), message).
                when().post(Endpoints.ADD_COMMENT.getValue()).
                then().statusCode(200).
                extract().jsonPath().getObject(RequestParams.RESPONSE.getValue(), Comment.class);
    }

    public static Like isLiked(Entry entry) {
        return given().
                queryParam(RequestParams.USER_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                queryParam(RequestParams.TYPE.getValue(), RequestParams.POST.getValue()).
                queryParam(RequestParams.OWNER_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                queryParam(RequestParams.ITEM_ID.getValue(), entry.getPost_id()).
                when().post(Endpoints.IS_LIKED.getValue()).
                then().statusCode(200).
                extract().jsonPath().getObject(RequestParams.RESPONSE.getValue(), Like.class);
    }

    public static DeletedEntry deleteEntry(Entry entry) {
        return given().
                queryParam(RequestParams.OWNER_ID.getValue(), USER_DATA.getValue(UserDataValue.ID.getValue())).
                queryParam(RequestParams.POST_ID.getValue(), entry.getPost_id()).
                when().post(Endpoints.DELETE.getValue()).
                then().statusCode(200).
                extract().as(DeletedEntry.class);
    }
}