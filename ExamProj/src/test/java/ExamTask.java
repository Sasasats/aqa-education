import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.ProjectForm;
import forms.ProjectsForm;
import models.Project;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.enums.ProjectsNames;
import utils.enums.QueryParams;
import utils.enums.SettingsFilesValues;

import java.util.List;

public class ExamTask extends BaseTest {
    private final static ISettingsFile settingsFile = new JsonSettingsFile("settings.json");

    private final ProjectsForm projectsForm = new ProjectsForm();
    private final ProjectForm projectForm = new ProjectForm();
    private final Steps steps = new Steps();

    @Test
    public void ExamTest() {
        logger.info("Получение токена запросом к API согласно номеру варианта");
        String token = APIUtils.getToken();
        Assert.assertFalse(token.isEmpty(), "Токен не сгенерирован");

        logger.info("Переход на сайт с авторизацией");
        BrowserUtils.authorisation();
        Assert.assertTrue(projectsForm.state().waitForDisplayed(), "Страница проекта не открыта");
        logger.info("Передача токена с помощью cookie");
        BrowserUtils.sendToken(token);
        logger.info("Обновление страницы");
        getBrowser().refresh();
        Assert.assertEquals(projectsForm.getActualVariant(),
                testData.getValue(SettingsFilesValues.VARIANT.getValue()), "Номер варианта не совпадает");

        String projID = projectsForm.getProjectId(ProjectsNames.NEXAGE.getValue());
        logger.info("Переход на страницу проекта \"Nexage\"");
        projectsForm.goToProject(ProjectsNames.NEXAGE.getValue());
        getBrowser().waitForPageToLoad();
        projectForm.waitForNotDisplayedModalWindow();
        logger.info("Получение списка тестов в JSON формате запросом к API");

        List<Project> projects = projectForm.getListWebTests();
        Assert.assertEquals(projectForm.getListWebTimes(projects), SortUtils.sortListReverseOrder(
                        projectForm.getListWebTimes(projects)),
                "Тесты, находящиеся на первой странице не отсортированы по убыванию даты");
        Assert.assertTrue(AssertUtils.assertEquals(projects, APIUtils.getListAPITimes(projID, projects.size())),
                "Тесты, находящиеся на первой странице не соответствуют тем, которые вернул запрос к апи");

        logger.info("Возврат на предыдущую страницу");
        getBrowser().goBack();
        steps.addProject(testData.getValue(SettingsFilesValues.PROJECT_NAME.getValue()).toString());
        logger.info("Обновление страницы");
        getBrowser().refresh();
        Assert.assertTrue(projectsForm.isProjectExist(testData.getValue(
                        SettingsFilesValues.PROJECT_NAME.getValue()).toString()),
                "После обновления страницы проект не отображается в списке");

        logger.info("Переход на страницу созданного проекта");
        projectsForm.goToProject(testData.getValue(SettingsFilesValues.PROJECT_NAME.getValue()).toString());

        String id = APIUtils.createTest(
                SID,
                testData.getValue(SettingsFilesValues.PROJECT_NAME.getValue()).toString(),
                testData.getValue(SettingsFilesValues.TEST_NAME.getValue()).toString(),
                testData.getValue(SettingsFilesValues.METHOD_NAME.getValue()).toString(),
                settingsFile.getValue(SettingsFilesValues.BROWSER_NAME.getValue()).toString());

        APIUtils.putLogs(id, RandomStringUtils.randomAlphabetic(15));
        APIUtils.putImageAttachment(id, getBrowser().getDriver().getScreenshotAs(OutputType.BASE64),
                QueryParams.CONTENT_IMAGE_TYPE.getValue());

        Assert.assertTrue(projectForm.getTest(testData.getValue(
                SettingsFilesValues.TEST_NAME.getValue()).toString()).state().waitForDisplayed());
    }
}