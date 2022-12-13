package group11.EventFiesta.DBConnection;

import group11.EventFiesta.application.PropertiesReader;

import java.io.InputStream;
import java.util.Properties;

public class DBConnectionProperties {

    String url;

    String username;

    String password;

    private static DBConnectionProperties dbConnectionProperties = null;

    //used singleton to load properties because the properties does not vary, can be fetched just once from the properties file
    public static DBConnectionProperties getInstance(String db) {
        if (dbConnectionProperties == null) {
            synchronized (DBConnectionProperties.class) {
                if (dbConnectionProperties == null) {
                    dbConnectionProperties = new DBConnectionProperties(db);
                }
            }
        }
        return dbConnectionProperties;
    }

    private DBConnectionProperties(String db) {
        loadDBProperties(db);
    }

    private void loadDBProperties(String db) {
        try {
            PropertiesReader propertiesReader = PropertiesReader.getInstance();
            Properties dbProp = propertiesReader.getProperties();
            url = dbProp.getProperty(db + ".datasource.url");
            username = dbProp.getProperty(db + ".datasource.username");
            password = dbProp.getProperty(db + ".datasource.password");
        } catch (Exception e) {
            System.out.println("Exception in DBConnectionProperties.loadDBProperties(): " + e.getMessage());
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
