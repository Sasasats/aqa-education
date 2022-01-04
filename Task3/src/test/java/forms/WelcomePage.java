package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {
    private static final String FORM_LOC = "//span[@class='FlatButton__in']";

    private final ITextBox phoneOrEmailTextBox = getElementFactory().getTextBox(By.id("index_email"),
            "Phone or email text box");
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(By.id("index_pass"),
            "Password text box");
    private final IButton signInButton = getElementFactory().getButton(By.id("index_login_button"),
            "Sign in button");

    public WelcomePage() {
        super(By.xpath(FORM_LOC), "Registration button");
    }

    public void enterPhone(String phone) {
        phoneOrEmailTextBox.clearAndType(phone);
    }

    public void enterPassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void signIn() {
        signInButton.clickAndWait();
    }
}