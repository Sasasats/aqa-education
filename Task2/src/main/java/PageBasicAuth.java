import org.openqa.selenium.By;

public class PageBasicAuth extends BaseForm {
    private TextField textField;

    public PageBasicAuth() {
        super("Page Basic Auth");
        this.textField = new TextField(By.xpath("//div[@class = \"example\"]//p"), "Text Result");
    }

    public String getTextResult() {
        return textField.getText();
    }
}