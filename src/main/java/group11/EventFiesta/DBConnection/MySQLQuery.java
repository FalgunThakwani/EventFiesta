package group11.EventFiesta.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLQuery {

    public ResultSet executeQuery(Connection connection, String query) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (Exception exception) {
            System.out.println("Exception in executeQuery():  " + exception.getMessage());
            exception.printStackTrace();
        }
        return resultSet;
    }

}
