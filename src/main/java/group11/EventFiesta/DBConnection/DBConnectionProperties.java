package group11.EventFiesta.DBConnection;

import java.io.InputStream;
import java.util.Properties;

public class DBConnectionProperties {

    String url;

    String username;

    String password;

    DBConnectionProperties(String db) {
        loadDBProperties(db);
    }

    private void loadDBProperties(String db) {
        Properties dbProp = new Properties();
        try {
            InputStream applicationProperties = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            dbProp.load(applicationProperties);
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
