import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import db.DatabaseConnection;
import db.TestDB;
import models.sqlTables.TestTable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import utils.enums.ColumnLabels;

import java.sql.Timestamp;

public class BaseTestForTestCase1 {
    protected final Logger logger = AqualityServices.getLogger();

    @AfterMethod
    public void afterMethod(ITestResult result) {
        logger.info("Добавление новой записи в БД о результате каждого выполненного теста в соответствующую таблицу");
        ISettingsFile projSettings = new JsonSettingsFile("projSettings.json");

        TestDB testDB = new TestDB();

        TestTable testTable = new TestTable();
        testTable.setName(result.getInstanceName());
        testTable.setStatusId(result.getStatus());
        testTable.setMethodName(result.getName());
        testTable.setProjectId((Integer) projSettings.getValue(ColumnLabels.PROJECT_ID.getValueJson()));
        testTable.setSessionId((Integer) projSettings.getValue(ColumnLabels.SESSION_ID.getValueJson()));
        testTable.setStartTime(new Timestamp(result.getStartMillis()));
        testTable.setEndTime(new Timestamp(result.getEndMillis()));
        testTable.setEnv(System.getenv(String.valueOf(projSettings.getValue(ColumnLabels.ENV.getValueJson()))));
        testTable.setBrowser(String.valueOf(projSettings.getValue(ColumnLabels.BROWSER.getValueJson())));
        testTable.setAuthorId((Integer) projSettings.getValue(ColumnLabels.AUTHOR_ID.getValueJson()));
        testDB.insert(testTable);
        logger.info("Запись добавлена");
    }

    @AfterSuite
    public void tearDown() {
        DatabaseConnection.closeConnection();
    }
}
