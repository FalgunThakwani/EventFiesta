package group11.EventFiesta.DBConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@SpringBootTest
public class MySQLQueryTest {

    @Test
    public void executeQueryTest() throws Exception {
        Connection connection = null;
        try {
            DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
            connection = dbConnectionPool.getConnection();
            MySQLQuery mySQLQuery = new MySQLQuery();
            ResultSet rs = mySQLQuery.executeQuery(connection, "select * from UserInfo");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println(columnCount);
            for(int i = 1; i <= columnCount; i++) {
                System.out.println(rsmd.getColumnName(i));
            }
            Assertions.assertEquals(columnCount, 4);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            connection.close();
        }
    }

}
