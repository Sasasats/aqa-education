import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import db.TestDB;
import models.sqlTables.TestTable;
import org.testng.annotations.Test;
import utils.enums.ColumnLabels;

public class TestCase2WorkingWithTestData extends BaseTestForTestCase2 {
    private static final ISettingsFile DEFAULT_STATUS_VALUES =
            new JsonSettingsFile("defaultStatusValues.json");

    private static final String SQL_GET_NEEDED_TESTS =
            "SELECT * FROM (SELECT * FROM test ORDER BY id DESC LIMIT %d) t ORDER BY id;";

    @Test
    public void workingWithTestData() {
        TestDB testDB = new TestDB();

        listForUpdate = testDB.get(String.format(SQL_GET_NEEDED_TESTS, testTables.size()));

        for (TestTable testTable : listForUpdate) {
            testTable.setStatusId((int) DEFAULT_STATUS_VALUES.getValue(ColumnLabels.ID.getValueJson()));
            testDB.update(testTable);
        }
    }
}
