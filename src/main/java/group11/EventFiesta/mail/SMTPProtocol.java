package group11.EventFiesta.mail;

import java.util.Properties;

public abstract class SMTPProtocol extends MailProtocol {

    public SMTPProtocol(String host, Integer port) {
        super(host, port);
    }

    public SMTPProtocol(String host, Integer port, Integer timeout) {
        super(host, port, timeout);
    }

    public Properties getMailProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.timeout", timeout);
        return properties;
    }
}
