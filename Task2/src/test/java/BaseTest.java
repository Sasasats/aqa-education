import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    private Properties config;
    private WebDriver driver;

    public BaseTest(){
        config = PropertiesReader.getPropertiesReader().getProperties("config.properties");
        this.driver = BrowserFactory.getDriver();
    }

    @BeforeTest
    protected void setUp(){
        driver.manage().window().maximize();
        BrowserFactory.goToURL(config.getProperty("HOST"));
    }

    @AfterTest
    protected void tearDown(){
        driver.quit();
    }
}
