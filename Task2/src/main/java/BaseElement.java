import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {
    private WebDriver driver;
    private By locator;
    private String name;

    public BaseElement(By locator, String name){
        this.driver = BrowserFactory.getDriver();
        this.locator = locator;
        this.name = name;
    }

    public void click(){
        findElement().click();
    }

    public By getLocator(){
        return locator;
    }

    protected WebElement findElement() {
        Waiter.waitingForElement(driver, locator);
        return driver.findElement(locator);
    }
}
