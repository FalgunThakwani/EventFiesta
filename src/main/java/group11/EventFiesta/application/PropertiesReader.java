package group11.EventFiesta.application;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties = new Properties();

    private static PropertiesReader propertiesReader = null;

    public static PropertiesReader getInstance() {
        if (propertiesReader == null) {
            synchronized (PropertiesReader.class) {
                if (propertiesReader == null) {
                    propertiesReader = new PropertiesReader();
                }
            }
        }
        return propertiesReader;
    }

    private PropertiesReader() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println("Exception in DBConnectionProperties.loadDBProperties(): " + e.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
