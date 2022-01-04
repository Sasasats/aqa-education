import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AlertUtils {
    private static WebDriver driver = BrowserFactory.getDriver();
    private static Logger logger = LoggerManager.getLogger();
    private static Alert alert;

    public static String randomText;

    private AlertUtils() {

    }

    public static void clickButtonOkAlert() {
        logger.log(Level.INFO, "Клик по кнопке 'Ok' в Alert");
        alert = driver.switchTo().alert();
        alert.accept();
        logger.log(Level.INFO, "Клик по кнопке 'Ok' в Alert произведен успешно");
    }

    public static String getTextAlert() {
        alert = driver.switchTo().alert();
        logger.log(Level.INFO, "Получен текст: " + alert.getText());
        return alert.getText();
    }

    public static void enterTextAlert(String randomText){
        logger.log(Level.INFO, "Ввод случайного текста в поле Alert");
        alert = driver.switchTo().alert();
        alert.sendKeys(randomText);
        logger.log(Level.INFO, "Ввод случайного текста в поле Alert произведен успешно");
    }
}
