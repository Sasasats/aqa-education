import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserFactory {
    private static WebDriver driver;
    private static Logger logger = LoggerManager.getLogger();
    private static Properties config = PropertiesReader.getPropertiesReader().getProperties("config.properties");
    private static Properties testData = PropertiesReader.getPropertiesReader().getProperties("testData.properties");

    private BrowserFactory() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (config.getProperty("BROWSER")) {
                case ("Chrome") -> driver = new ChromeDriver();
                case ("Firefox") -> driver = new FirefoxDriver();
                default -> throw new RuntimeException("Incorrect BrowserName");
            }
        }
        return driver;
    }

    public static void goToURL(String url) {
        logger.log(Level.INFO, "Переход по URL: " + url);
        driver.get(url);
        logger.log(Level.INFO, "Переход по URL произведен успешно");
    }
}