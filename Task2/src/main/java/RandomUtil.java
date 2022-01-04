import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomUtil {

    private static Logger logger = LoggerManager.getLogger();
    public static String randomText;

    public static String getRandomText(){
        logger.log(Level.INFO, "Создание случайного текста");
        String uuid = UUID.randomUUID().toString();
        logger.log(Level.INFO, "Создание случайного текста произведено успешно");
        randomText = uuid.replace("-", "");
        return randomText;
    }
}
