package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private final IButton myPageButton = getElementFactory().getButton(By.id("l_pr"),
            "'My page' element side bar");

    private static final String FORM_LOC = "//li[@class='HeaderNav__item']";

    public MainPage() {
        super(By.xpath(FORM_LOC), "User navigation item");
    }

    public void goToMyPage() {
        myPageButton.state().waitForClickable();
        myPageButton.clickAndWait();
    }
}