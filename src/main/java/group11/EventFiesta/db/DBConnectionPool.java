package group11.EventFiesta.db;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {

    private static DBConnectionPool dbcpDataSource = null;

    public static DBConnectionPool getInstance(DBConnectionProperties properties) {
        if (dbcpDataSource == null) {
            synchronized (DBConnectionPool.class) {
                if (dbcpDataSource == null) {
                    dbcpDataSource = new DBConnectionPool(properties);
                }
            }
        }
        return dbcpDataSource;
    }

    private static BasicDataSource ds = null;

    private DBConnectionPool(DBConnectionProperties dbProperties) {
        ds = new BasicDataSource();
        ds.setUrl(dbProperties.getUrl());
        ds.setUsername(dbProperties.getUsername());
        ds.setPassword(dbProperties.getPassword());
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }


    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
