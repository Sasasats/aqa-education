import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerManager {
    private static Logger logger;

    private LoggerManager() {

    }

    public static Logger getLogger() {
        if (logger == null) {
            try (FileInputStream fis = new FileInputStream("./src/main/resources/log.properties")) {
                LogManager.getLogManager().readConfiguration(fis);
                logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return logger;
    }
}
