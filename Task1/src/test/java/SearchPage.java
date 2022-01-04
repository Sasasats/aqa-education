import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    By sortTriggerLocator = By.id("sort_by_trigger");
    By sortPriceDescLocator = By.id("Price_DESC");
    By rangePriceLocator = By.className("range_input");
    By gamesResultsLocator = By.xpath("//div[@id = \"search_resultsRows\"]//a");
    By pricesResultsLocator = By.xpath("//div[@class = \"col search_price  responsive_secondrow\"]");

    private final WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean checkPage() {
        return driver.findElement(rangePriceLocator).isDisplayed();
    }

    public boolean checkNotEmptyListGames() {
        return !driver.findElements(gamesResultsLocator).isEmpty();
    }

    public boolean sortingByPriceDesc() {
        List<WebElement> elements = driver.findElements(pricesResultsLocator);

        driver.findElement(sortTriggerLocator).click();
        driver.findElement(sortPriceDescLocator).click();

        wait.until(ExpectedConditions.stalenessOf(elements.get(0)));

        elements = driver.findElements(pricesResultsLocator);

        String price1 = elements.get(0).getText().toString();
        String price2 = elements.get(1).getText().toString();

        if (price1.compareTo(price2) > 0)
            return true;
        else
            return false;
    }
}