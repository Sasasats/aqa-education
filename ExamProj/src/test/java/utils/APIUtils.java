package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import models.Project;
import utils.enums.Endpoints;
import utils.enums.QueryParams;
import utils.enums.SettingsFilesValues;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class APIUtils {
    private final static ISettingsFile testData = new JsonSettingsFile("testData.json");

    public static String getToken() {
        return given().
                queryParam(QueryParams.VARIANT.getValue(), testData.getValue(SettingsFilesValues.VARIANT.getValue())).
                when().post(Endpoints.GENERATE_TOKEN.getValue()).
                then().statusCode(200).
                extract().asString();
    }

    public static List<Project> getTestsListJson(String projectId) {
        return given().
                queryParam(QueryParams.PROJECT_ID.getValue(), projectId).
                when().post(Endpoints.GET_LIST_TESTS_JSON.getValue()).
                then().statusCode(200).
                extract().jsonPath().getList("", Project.class);
    }

    public static List<Project> getListAPITimes(String projId, Integer listSize) {
        List<Project> sortedProjects = SortUtils.sortListForTimeReverseOrder(getTestsListJson(projId));
        return sortedProjects.subList(0, listSize);
    }

    public static String createTest(
            String SID, String projectName, String testName, String methodName, String browser) {

        String env = null;
        try {
            env = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            AqualityServices.getLogger().info(String.valueOf(e));
        }

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        return given().
                queryParam(QueryParams.SID.getValue(), SID).
                queryParam(QueryParams.PROJECT_NAME.getValue(), projectName).
                queryParam(QueryParams.TEST_NAME.getValue(), testName).
                queryParam(QueryParams.METHOD_NAME.getValue(), methodName).
                queryParam(QueryParams.ENV.getValue(), env).
                queryParam(QueryParams.START_TIME.getValue(), formatForDateNow.format(dateNow)).
                queryParam(QueryParams.BROWSER.getValue(), browser).
                when().post(Endpoints.CREATE_TEST_POST.getValue()).
                then().statusCode(200).
                extract().asString();
    }

    public static void putImageAttachment(String testId, String screenshot, String contentType) {
        given().
                queryParam(QueryParams.TEST_ID.getValue(), testId).
                queryParam(QueryParams.CONTENT.getValue(), screenshot).
                queryParam(QueryParams.CONTENT_TYPE.getValue(), contentType).
                when().post(Endpoints.SEND_TEST_ATTACHMENT.getValue()).
                then().statusCode(200).
                extract().asString();
    }

    public static void putLogs(String testId, String logs) {
        given().
                queryParam(QueryParams.TEST_ID.getValue(), testId).
                queryParam(QueryParams.CONTENT.getValue(), logs).
                when().post(Endpoints.SEND_TEST_LOG.getValue()).
                then().statusCode(200).
                extract().asString();
    }
}