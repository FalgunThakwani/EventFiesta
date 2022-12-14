package group11.EventFiesta.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@SpringBootTest
public class PropertyReaderTest {

    @Test
    public void getPropertiesSuccessTest() {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        Properties applicationProperties = propertiesReader.getProperties();

        List<String> existingProperties = new LinkedList<>();
        existingProperties.add("mysql.datasource.url");
        existingProperties.add("mysql.datasource.username");
        existingProperties.add("mysql.datasource.password");

        existingProperties.add("event_fiesta.default_email.email");
        existingProperties.add("event_fiesta.default_email.app_password");

        existingProperties.add("event_fiesta.excluded_url");

        for (String property : existingProperties) {
            Assertions.assertNotNull(applicationProperties.getProperty(property));
        }
    }

    @Test
    public void getPropertiesFailureTest() {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        Properties applicationProperties = propertiesReader.getProperties();
        String removedProperty = "event_fiesta.property_removed";
        Assertions.assertNull(applicationProperties.getProperty(removedProperty));
    }

}
