import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage {
    By inputGameLocator = By.name("term");
    By imageMainPageLocator = By.className("home_page_gutter_giftcard");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkPage() {
        return driver.findElement(imageMainPageLocator).isDisplayed();
    }

    public void enterGameName(String game) {
        driver.findElement(inputGameLocator).sendKeys(game);
        driver.findElement(inputGameLocator).sendKeys(Keys.ENTER);
    }
}