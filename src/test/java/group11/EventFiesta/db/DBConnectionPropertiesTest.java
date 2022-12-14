package group11.EventFiesta.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBConnectionPropertiesTest {

    @Test
    public void getInstanceFailureTest() {
        DBConnectionProperties dbConnectionProperties = new DBConnectionProperties("random");
        Assertions.assertNull(dbConnectionProperties.getUrl());
        Assertions.assertNull(dbConnectionProperties.getUsername());
        Assertions.assertNull(dbConnectionProperties.getPassword());
    }

    @Test
    public void getInstanceSuccesTest() {
        DBConnectionProperties dbConnectionProperties = new DBConnectionProperties("mysql");
        Assertions.assertNotNull(dbConnectionProperties);
        Assertions.assertEquals("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_11_DEVINT", dbConnectionProperties.getUrl());
        Assertions.assertEquals("CSCI5308_11_DEVINT_USER", dbConnectionProperties.getUsername());
        Assertions.assertEquals("DhErnQD2UR", dbConnectionProperties.getPassword());
    }
}
