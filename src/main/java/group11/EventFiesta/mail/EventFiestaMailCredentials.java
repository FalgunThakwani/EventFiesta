package group11.EventFiesta.mail;

import group11.EventFiesta.application.PropertiesReader;

import java.util.Properties;

public class EventFiestaMailCredentials {

    private String email;

    private String appPassword;

    public EventFiestaMailCredentials() {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        Properties applicationProperties = propertiesReader.getProperties();
        email = applicationProperties.getProperty("event_fiesta.default_email.email");
        appPassword = applicationProperties.getProperty("event_fiesta.default_email.app_password");
    }

    public String getEmail() {
        return email;
    }

    public String getAppPassword() {
        return appPassword;
    }
}
