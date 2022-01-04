import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    private static Waiter waiter;

    public static Waiter getWaiter(){
        if(waiter == null){
            waiter = new Waiter();
        }
        return waiter;
    }

    private Waiter(){

    }

    public static void waitingForElement(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
