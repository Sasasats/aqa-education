import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import db.DatabaseConnection;
import db.TestDB;
import models.sqlTables.TestTable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.RandomUtils;
import utils.enums.ColumnLabels;

import java.util.ArrayList;
import java.util.List;

public class BaseTestForTestCase2 {
    protected final Logger logger = AqualityServices.getLogger();

    protected List<TestTable> testTables = new ArrayList<>();
    protected List<TestTable> listForUpdate;

    private static final TestDB TEST_DB = new TestDB();
    private static final ISettingsFile PROJ_SETTINGS = new JsonSettingsFile("projSettings.json");

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Выборка не более 10 тестов из БД где id содержит в себе две случайных повторяющихся цифры");
        List<TestTable> testTableList = TEST_DB.getAll();
        int digitCapacity = 2;
        int selectorId = RandomUtils.getRandomRepeatingNumber(digitCapacity);
        int listSize = 10;

        for (TestTable testTable : testTableList) {
            if (testTable.getId().toString().contains(String.valueOf(selectorId))) {
                testTables.add(testTable);
            }
            if (testTables.size() >= listSize) {
                break;
            }
        }
        logger.info("Выборка завершена");

        logger.info("Копирование тестов с указанием текущего проекта и автора");
        for (TestTable testTable : testTables) {
            testTable.setProjectId((Integer) PROJ_SETTINGS.getValue(ColumnLabels.PROJECT_ID.getValueJson()));
            testTable.setAuthorId((Integer) PROJ_SETTINGS.getValue(ColumnLabels.AUTHOR_ID.getValueJson()));
            TEST_DB.insert(testTable);
        }
        logger.info("Копирование тестов с указанием текущего проекта и автора завершено");
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Удаление созданных записей из БД");
        for (TestTable testTable : listForUpdate) {
            TEST_DB.delete(testTable);
        }
        logger.info("Удаление созданных записей из БД завершено");
    }

    @AfterSuite
    public void tearDown() {
        DatabaseConnection.closeConnection();
    }
}
