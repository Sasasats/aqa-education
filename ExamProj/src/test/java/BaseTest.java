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
import utils.enums.SettingsFilesValues;

public class BaseTest {
    protected Logger logger = AqualityServices.getLogger();

    protected ISettingsFile testData = new JsonSettingsFile("testData.json");
    protected ISettingsFile configData = new JsonSettingsFile("config.json");
    protected String SID;

    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().maximize();
    }

    @BeforeSuite
    protected void beforeSuite() {
        SID = java.time.LocalDateTime.now().toString();

        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri(configData.getValue(SettingsFilesValues.BASE_URI_API.getValue()).toString()).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    @AfterMethod
    public void afterMethod() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}