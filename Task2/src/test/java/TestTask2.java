import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestTask2 extends BaseTest {

    private TestTask2() {
        super();
    }

    private Logger logger = LoggerManager.getLogger();

    @Test
    public void Test1() {
        Properties testData = PropertiesReader.getPropertiesReader().getProperties("testData.properties");
        PageBasicAuth pageBasicAuth = new PageBasicAuth();

        logger.log(Level.INFO, "ТЕСТ 1");

        BrowserFactory.goToURL(BrowserUtil.getAuthUrl());

        logger.log(Level.INFO, "Проверка на отображаемый текст");
        Assert.assertEquals(pageBasicAuth.getTextResult(), testData.getProperty("TEXTBASICAUTH"));
        logger.log(Level.INFO, "Проверка прошла успешно");

        logger.log(Level.INFO, "ТЕСТ 1 Конец\n\n");
    }

    @Test
    public void Test2_Alerts() {
        logger.log(Level.INFO, "ТЕСТ 2");
        Properties config = PropertiesReader.getPropertiesReader().getProperties("config.properties");
        Properties testData = PropertiesReader.getPropertiesReader().getProperties("testData.properties");

        BrowserFactory.goToURL(config.getProperty("HOST") + testData.getProperty("PATH2"));

        PageAlerts pageAlerts = new PageAlerts();
        pageAlerts.waitForOpen();

        pageAlerts.clickButtonJSAlert();
        logger.log(Level.INFO, "Проверка на отображение текста в Alert " + testData.getProperty("TEXTALERT1"));
        Assert.assertEquals(AlertUtils.getTextAlert(), testData.getProperty("TEXTALERT1"), "Ошибка, текст не совпадает");
        logger.log(Level.INFO, "'I am a JS Alert' text is displayed");
        AlertUtils.clickButtonOkAlert();
        logger.log(Level.INFO, "Проверка на отображение текста " + testData.getProperty("TEXTRESULTSECTION1"));
        Assert.assertEquals(pageAlerts.getTextResult(), testData.getProperty("TEXTRESULTSECTION1"), "Ошибка, текст не совпадает");
        logger.log(Level.INFO, "'You successfully clicked an alert' text is displayed in Result section");

        pageAlerts.clickButtonJSConfirm();
        logger.log(Level.INFO, "Проверка на отображение текста в Alert " + testData.getProperty("TEXTALERT2"));
        Assert.assertEquals(AlertUtils.getTextAlert(), testData.getProperty("TEXTALERT2"), "Ошибка, текст не совпадает");
        logger.log(Level.INFO, "'I am a JS Confirm' text is displayed");
        AlertUtils.clickButtonOkAlert();
        logger.log(Level.INFO, "Проверка на отображение текста " + testData.getProperty("TEXTRESULTSECTION2"));
        Assert.assertEquals(pageAlerts.getTextResult(), testData.getProperty("TEXTRESULTSECTION2"), "Ошибка, текст не совпадает");
        logger.log(Level.INFO, "' You clicked: OK' text is displayed in Result section");

        pageAlerts.clickButtonJSPrompt();
        logger.log(Level.INFO, "Проверка на отображение текста в Alert " + testData.getProperty("TEXTALERT3"));
        Assert.assertEquals(AlertUtils.getTextAlert(), testData.getProperty("TEXTALERT3"), "Ошибка, текст не совпадает");
        logger.log(Level.INFO, "'I am a JS Confirm' text is displayed");
        AlertUtils.enterTextAlert(RandomUtil.getRandomText());
        AlertUtils.clickButtonOkAlert();
        logger.log(Level.INFO, "Проверка на отображение текста");
        Assert.assertEquals(pageAlerts.getTextResult(), testData.getProperty("TEXTRESULTSECTION3") + RandomUtil.randomText);
        logger.log(Level.INFO, "Проверка на отображение текста произведена успешно");

        logger.log(Level.INFO, "ТЕСТ 2 Конец");
    }
}
