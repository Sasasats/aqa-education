package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import models.Project;
import org.openqa.selenium.By;
import utils.enums.ColumnNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectForm extends Form {
    private final ITextBox modalWindow = getElementFactory().getTextBox(By.xpath("//div[@class='messi-modal']"),
            "messi modal");

    private static final String FORM_LOC = "//div[@class='panel-heading']";
    private static final String TEST_XPATH = "//td//a[text()='%s']";
    private static final String TABLE_XPATH = "//table[@class='table']";

    public ProjectForm() {
        super(By.xpath(FORM_LOC), "locator");
    }

    public List<Project> getListWebTests() {
        List<ITextBox> columnNames = getTableColumnNames(getTable());
        List<ITextBox> rows = getTableRows(getTable());

        List<Project> projects = new ArrayList<>();
        Project project = new Project();

        for (int i = 1; i < rows.size(); i++) {
            List<ITextBox> cols = rows.get(i).findChildElements(By.tagName("td"), ElementType.TEXTBOX);

            for (int j = 0; j < cols.size() - 1; j++) {

                if (Objects.equals(columnNames.get(j).getText(), ColumnNames.TEST_NAME.getValue())) {
                    project.setName(cols.get(j).getText());
                } else if (Objects.equals(columnNames.get(j).getText(), ColumnNames.TEST_METHOD.getValue())) {
                    project.setMethod(cols.get(j).getText());
                } else if (Objects.equals(columnNames.get(j).getText(), ColumnNames.LATEST_TEST_RESULT.getValue())) {
                    project.setStatus(cols.get(j).getText());
                } else if (Objects.equals(columnNames.get(j).getText(),
                        ColumnNames.LATEST_TEST_START_TIME.getValue())) {
                    project.setStartTime(cols.get(j).getText());
                } else if (Objects.equals(columnNames.get(j).getText(), ColumnNames.LATEST_TEST_END_TIME.getValue())) {
                    project.setEndTime(cols.get(j).getText());
                } else if (Objects.equals(columnNames.get(j).getText(), ColumnNames.LATEST_TEST_DURATION.getValue())) {
                    project.setDuration(cols.get(j).getText());
                }
            }
            projects.add(project);
            project = new Project();
        }
        return projects;
    }

    public List<String> getListWebTimes(List<Project> projects) {
        List<String> listWebTimes = new ArrayList<>();
        for (Project project : projects) {
            listWebTimes.add(project.getStartTime());
        }
        return listWebTimes;
    }

    public ITextBox getTable() {
        return getElementFactory().getTextBox(By.xpath(TABLE_XPATH), "table");
    }

    public List<ITextBox> getTableRows(ITextBox table) {
        return table.findChildElements(By.tagName("tr"), ElementType.TEXTBOX);
    }

    public List<ITextBox> getTableColumnNames(ITextBox table) {
        return table.findChildElements(By.tagName("th"), ElementType.TEXTBOX);
    }

    public ITextBox getTest(String testName) {
        return getElementFactory().getTextBox(By.xpath(String.format(TEST_XPATH, testName)), "test");
    }

    public void waitForNotDisplayedModalWindow() {
        modalWindow.state().waitForNotDisplayed();
    }
}