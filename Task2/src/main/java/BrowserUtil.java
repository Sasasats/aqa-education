import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserUtil {
    private static Logger logger = LoggerManager.getLogger();

    public static String getAuthUrl() {
        logger.log(Level.INFO, "Создание URL для входа");
        Properties config = PropertiesReader.getPropertiesReader().getProperties("config.properties");
        Properties testData = PropertiesReader.getPropertiesReader().getProperties("testData.properties");

        String host = config.getProperty("HOST");
        String[] substr = host.split("//", 2);
        String authUrl = substr[0] + "admin:" + "admin@" + substr[1] + testData.getProperty("PATH1");
        logger.log(Level.INFO, "Создание URL для входа прошло успешно");
        return authUrl;
    }
}
