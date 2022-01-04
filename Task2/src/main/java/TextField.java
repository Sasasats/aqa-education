import org.openqa.selenium.By;

public class TextField extends BaseElement {
    public TextField(By locator, String name) {
        super(locator, name);
    }

    public String getText() {
        return findElement().getText();
    }
}