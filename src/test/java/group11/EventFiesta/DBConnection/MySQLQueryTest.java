package group11.EventFiesta.DBConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class MySQLQueryTest {

    @Test
    public void executeQueryTest() {
        try {
            //todo create mock DB
            MySQLDBPersistence mySQLQuery = new MySQLDBPersistence();
            ArrayList<HashMap<String, Object>> result = mySQLQuery.loadData("select * from UserInfo");
            Assertions.assertEquals(result.size(), 1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
