package group11.EventFiesta.DBConnection;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {

    private static DBConnectionPool dbcpDataSource = null;

    public static DBConnectionPool getInstance() {
        if (dbcpDataSource == null) {
            synchronized (DBConnectionPool.class) {
                if (dbcpDataSource == null) {
                    dbcpDataSource = new DBConnectionPool();
                }
            }
        }
        return dbcpDataSource;
    }

    private static BasicDataSource ds = null;

    private DBConnectionPool() {
        ds = new BasicDataSource();

        //todo need to get db configurations from application.properties file
        ds.setUrl("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_11_DEVINT");
        ds.setUsername("CSCI5308_11_DEVINT_USER");
        ds.setPassword("DhErnQD2UR");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }


    public Connection getConnection() throws SQLException {
        ds.getNumActive();
        return ds.getConnection();
    }

}
