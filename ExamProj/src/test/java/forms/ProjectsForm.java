package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsForm extends Form {
    private static final String FORM_LOC = "//div[@class='panel-heading']";
    private static final String BUTTON_LOC_PATTERN = "//a[text()='%s']";

    private final IButton buttonAddProject = getElementFactory().getButton(By.xpath("//a[@href='addProject']"),
            "button add project");

    private final ILabel versionText = getElementFactory().
            getLabel(By.xpath("//p[contains(@class, 'footer-text')]//span"), "footer version text");

    public ProjectsForm() {
        super(By.xpath(FORM_LOC), "locator");
    }

    public String getActualVariant() {
        return getLastNum(versionText.getText());
    }

    public void addProject() {
        buttonAddProject.clickAndWait();
    }

    private IButton getNeededProject(String projectName) {
        return getElementFactory().getButton(By.xpath(String.format(BUTTON_LOC_PATTERN, projectName)),
                "project " + projectName);
    }

    public String getProjectId(String projectName) {
        return getLastNum(getNeededProject(projectName).getAttribute("href"));
    }

    public boolean isProjectExist(String projectName) {
        return getElementFactory().getTextBox(By.xpath(String.format(BUTTON_LOC_PATTERN, projectName)),
                "project " + projectName).state().isDisplayed();
    }

    public void goToProject(String projectName) {
        getNeededProject(projectName).clickAndWait();
    }

    private String getLastNum(String text) {
        char[] ch = text.toCharArray();
        int index = 0;
        String num = "";

        for (int i = ch.length - 1; i > 0; i--) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                index = i;
            } else {
                break;
            }
        }
        num = text.substring(index, text.length());
        return num;
    }
}