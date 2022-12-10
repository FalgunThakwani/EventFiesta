package group11.EventFiesta.mail;

import java.util.Properties;

public abstract class MailHandler {

    String host;

    Integer port;

    Integer timeout = 3000;

    public MailHandler(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public MailHandler(String host, Integer port, Integer timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public Properties getMailProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.timeout", timeout);
        return properties;
    }
}
