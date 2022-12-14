package group11.EventFiesta.db;

import group11.EventFiesta.application.PropertiesReader;

import java.util.Properties;

public class DBConnectionProperties {

    private String url;

    private String username;

    private String password;

    public DBConnectionProperties(String db) {
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
