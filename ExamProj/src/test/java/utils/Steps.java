package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import forms.AddProjectForm;
import forms.ProjectsForm;
import org.testng.Assert;
import utils.enums.JavaScriptsMethods;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class Steps {
    private final Logger logger = AqualityServices.getLogger();

    private final ProjectsForm projectsForm = new ProjectsForm();
    private final AddProjectForm addProjectForm = new AddProjectForm();

    public void addProject(String projName) {
        logger.info("Нажатие на +Add");
        projectsForm.addProject();
        getBrowser().tabs().switchToLastTab();
        logger.info("Ввод название проекта");
        addProjectForm.inputProjectName(projName);
        logger.info("Сохранение проекта");
        addProjectForm.saveProject();
        Assert.assertTrue(addProjectForm.isAlertSuccessDisplayed(),
                "Сообщение об успешном сохранении не появилось");
        logger.info("Закрытие окна добавления проекта с помощью JavaScript метода close()");
        getBrowser().executeScript(JavaScriptsMethods.CLOSE.getValue());
        Assert.assertFalse(addProjectForm.state().isDisplayed(), "Окно добавления проекта не закрыто");
        getBrowser().tabs().switchToLastTab();
    }
}