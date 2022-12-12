package group11.EventFiesta.DBConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MySQLQueryTest {

    @Test
    public void executeQueryTest() {
        try {
            //todo create mock DB
            MySQLDBPersistence mySQLQuery = new MySQLDBPersistence();
            List<Map<String, Object>> result = mySQLQuery.loadData("select * from UserInfo");
            Assertions.assertTrue(result.size()>0);
           // Assertions.assertEquals(result.size(), 0);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
