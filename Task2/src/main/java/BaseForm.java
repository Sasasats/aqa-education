import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class BaseForm {
    private String name;
    private WebDriver driver;
    private Logger logger;

    public BaseForm(String name) {
        this.name = name;
        this.driver = BrowserFactory.getDriver();
        this.logger = LoggerManager.getLogger();
    }
}
