package group11.EventFiesta.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLDBPersistence implements IDBPersistence {

    public ArrayList<HashMap<String, Object>> loadData(String query) throws Exception {

        DBConnectionPool connectionPool = DBConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<HashMap<String, Object>> rows = new ArrayList();
        try {
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                HashMap<String, Object> row = new HashMap<>();
                int column_count = rsmd.getColumnCount();
                for (int i = 0; i < column_count; i++) {
                    row.put(rsmd.getColumnName(i), resultSet.getObject(i + 1));
                }
                rows.add(row);
            }
        } catch (Exception exception) {
            System.out.println("Exception in loadData():  " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }
        return rows;
    }

    public Integer saveData(String query) throws Exception {
        Connection connection;
        PreparedStatement statement = null;
        Integer result = -1;
        try {
            DBConnectionPool connectionPool = DBConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(query);

            result = statement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Exception in saveData():  " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            statement.close();
        }
        return result;
    }

}