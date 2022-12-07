package group11.EventFiesta.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLDBPersistence implements IDBPersistence {

    private DBConnectionPool connectionPool;

    public MySQLDBPersistence() {
        DBConnectionProperties properties = new DBConnectionProperties("mysql");
        connectionPool = DBConnectionPool.getInstance(properties);
    }

    public ArrayList<HashMap<String, Object>> loadData(String query) throws Exception {
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
                    row.put(rsmd.getColumnName(i + 1), resultSet.getObject(i + 1));
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

    public ArrayList<HashMap<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
        Connection connection = connectionPool.getConnection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<HashMap<String, Object>> rows = new ArrayList();
        try {
            statement = connection.prepareCall("{call " + storedProcedure + "(?, ?, ?, ?)}");

            int pi = 1;
            for (Object param : params) {
                statement.setObject(pi++, param);
            }

            boolean hasResult = statement.execute();

            if(hasResult) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    HashMap<String, Object> row = new HashMap<>();
                    int column_count = rsmd.getColumnCount();
                    for (int i = 0; i < column_count; i++) {
//                        System.out.println(rsmd.getTableName(i+1));
//                        System.out.println(rsmd.getColumnName(i+1));
                        row.put(rsmd.getColumnName(i + 1), resultSet.getObject(i + 1));
                    }
                    rows.add(row);
                }
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
