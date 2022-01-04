import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader propertiesReader;
    private Properties properties;

    public static PropertiesReader getPropertiesReader() {
        if (propertiesReader == null) {
            propertiesReader = new PropertiesReader();
        }
        return propertiesReader;
    }

    private PropertiesReader() {
        properties = new Properties();
    }

    public Properties getProperties(String fileName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}