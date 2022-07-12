import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.RequestParams;
import utils.SettingsValue;
import utils.UserDataValue;

public class BaseTest {
    protected final Logger logger = AqualityServices.getLogger();
    private final ISettingsFile userData = new JsonSettingsFile("userAuthorizationData.json");
    private final ISettingsFile settingsData = new JsonSettingsFile("settingsData.json");

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getLogger().info("Go to the site " + settingsData.getValue(SettingsValue.URI.getValue()));
        getBrowser().goTo(settingsData.getValue(SettingsValue.URI.getValue()).toString());
        getBrowser().maximize();
        getBrowser().waitForPageToLoad();
    }

    @BeforeSuite
    protected void beforeSuite() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri(settingsData.getValue(SettingsValue.BASE_REQUEST.getValue()).toString()).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                log(LogDetail.ALL).
                addQueryParam(RequestParams.ACCESS_TOKEN.getValue(), userData.getValue(UserDataValue.TOKEN.getValue())).
                addQueryParam(RequestParams.V.getValue(), settingsData.getValue(SettingsValue.VERSION_API.getValue())).
                build();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}