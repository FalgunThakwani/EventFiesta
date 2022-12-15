package group11.EventFiesta.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLDBPersistence implements IDBPersistence {

    private DBConnectionPool connectionPool;

    public MySQLDBPersistence() {
        DBConnectionProperties properties = new DBConnectionProperties("mysql");
        connectionPool = DBConnectionPool.getInstance(properties);
    }

    public List<Map<String, Object>> loadData(String query) throws Exception {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> rows = new ArrayList<>();
        try {
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                Map<String, Object> row = new HashMap<>();
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
            if (resultSet != null) {
                resultSet.close();
            }
            statement.close();
            connection.close();
        }
        return rows;
    }

    public List<Map<String, Object>> loadData(String storedProcedure, Object... params) throws Exception {
        Connection connection = connectionPool.getConnection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> rows = new ArrayList<>();

        try {
            statement = connection.prepareCall(spPrepareStatement(storedProcedure, params));
            int pi = 1;
            for (Object param : params) {
                statement.setObject(pi++, param);
            }

            boolean hasResult = statement.execute();

            if (hasResult) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    Map<String, Object> row = new HashMap<>();
                    int column_count = rsmd.getColumnCount();
                    for (int i = 0; i < column_count; i++) {
                        row.put(rsmd.getColumnName(i + 1), resultSet.getObject(i + 1));
                    }
                    rows.add(row);
                }
            }
        } catch (Exception exception) {
            System.out.println("Exception in loadData():  " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            statement.close();
            connection.close();
        }
        return rows;
    }

    public Integer saveData(String query, Object... params) throws Exception {
        Connection connection = connectionPool.getConnection();

        CallableStatement statement = null;

        Integer result = -1;
        try {
            statement = connection.prepareCall(query);

            int pi = 1;
            for (Object param : params) {
                statement.setObject(pi++, param);
            }

            result = statement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Exception in saveData():  " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
        return result;
    }

    public String spPrepareStatement(String storedProcedure, Object[] params) {
        String prepareCallString = "{call " + storedProcedure + " (";
        for (Object param : params) {
            prepareCallString += "?,";
        }
        StringBuffer buffer = new StringBuffer(prepareCallString);
        buffer.deleteCharAt(prepareCallString.length() - 1);
        prepareCallString = buffer.toString();
        prepareCallString += ")}";
        return prepareCallString;
    }

    private String spPrepareStatement(String storedProcedure, Object[] params, int[] outputParams) {
        String prepareCallString = "{call " + storedProcedure + " (";
        for (Object param : params) {
            prepareCallString += "?,";
        }
        for (Object param : outputParams) {
            prepareCallString += "?,";
        }
        StringBuffer buffer = new StringBuffer(prepareCallString);
        buffer.deleteCharAt(prepareCallString.length() - 1);
        prepareCallString = buffer.toString();
        prepareCallString += ")}";
        return prepareCallString;
    }

    public Integer updateData(String storedProcedure, Object... params) throws Exception {
        String query = spPrepareStatement(storedProcedure, params);

        System.out.println("Constructed query for stored procedure: " + query);

        return saveData(query, params);
    }

    public List<Object> insertData(String insertProcedure, Object[] inputParams, int[] outputParams) throws Exception {
        Connection connection = connectionPool.getConnection();
        CallableStatement statement = null;
        ResultSet resultSet = null;
        List<Object> returnValues = new ArrayList<>();

        try {
            statement = connection.prepareCall(spPrepareStatement(insertProcedure, inputParams, outputParams));
            int parameterIndex = 1;
            for (Object param : inputParams) {
                statement.setObject(parameterIndex++, param);
            }
            int outParamIndex = parameterIndex;
            for (int param : outputParams) {
                System.out.println(parameterIndex);
                statement.registerOutParameter(parameterIndex++, param);
            }

            boolean hasResult = statement.execute();
            System.out.println(hasResult);

                for (int i = outParamIndex, j = 0; j < outputParams.length; i++, j++) {
                    System.out.println(outParamIndex + " " + statement.getObject(outParamIndex));
                    returnValues.add(statement.getObject(outParamIndex));
                }
        } catch (Exception exception) {
            System.out.println("Exception in loadData():  " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            statement.close();
            connection.close();
        }
        return returnValues;
    }

}
