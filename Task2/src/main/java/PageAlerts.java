import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PageAlerts extends BaseForm {
    private WebDriver driver;
    private Logger logger;
    private Bookmark bookmark;
    private Button buttonJSAlert;
    private Button buttonJSConfirm;
    private Button buttonJSPrompt;
    private TextField textField;

    private static final String xpathToButton = "//button[@onclick = '%s']";

    public PageAlerts() {
        super("Page Alerts");
        this.driver = BrowserFactory.getDriver();
        this.logger = LoggerManager.getLogger();
        this.bookmark = new Bookmark(By.xpath("//img[@alt = \"Fork me on GitHub\"]"), "Angle Bookmark");
        this.buttonJSAlert = new Button(By.xpath(String.format(xpathToButton, "jsAlert()")), "Button JS Alert");
        this.buttonJSConfirm = new Button(By.xpath(String.format(xpathToButton, "jsConfirm()")), "Button JS Confirm");
        this.buttonJSPrompt = new Button(By.xpath(String.format(xpathToButton, "jsPrompt()")), "Button JS Prompt");
        this.textField = new TextField(By.id("result"), "Text Result");
    }

    public void clickButtonJSAlert() {
        logger.log(Level.INFO, "Клик по кнопке 'Click for JS Alert'");
        buttonJSAlert.click();
        logger.log(Level.INFO, "Клик по кнопке 'Click for JS Alert' произведен успешно");
    }

    public void clickButtonJSConfirm() {
        logger.log(Level.INFO, "Клик по кнопке 'Click for JS Config'");
        buttonJSConfirm.click();
        logger.log(Level.INFO, "Клик по кнопке 'Click for JS Config' произведен успешно");
    }

    public void clickButtonJSPrompt() {
        logger.log(Level.INFO, "Клик по кнопке 'Click for JS Prompt'");
        buttonJSPrompt.click();
        logger.log(Level.INFO, "Клик по кнопке 'Click for JS Prompt' произведен успешно");
    }

    public String getTextResult() {
        return textField.getText();
    }

    public void waitForOpen(){
        logger.log(Level.INFO, "Ожидание прогрузки страницы");
        Waiter.waitingForElement(driver, bookmark.getLocator());
        logger.log(Level.INFO, "Ожидание прогрузки страницы произведено успешно");
    }
}