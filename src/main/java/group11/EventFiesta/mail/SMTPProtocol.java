package group11.EventFiesta.mail;

import javax.mail.Authenticator;
import java.util.Properties;

public abstract class SMTPProtocol extends MailProtocol {

    public SMTPProtocol(String host, int port) {
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

    public Authenticator getAuthenticator(EventFiestaMailCredentials efCredentials) {
        return new SMTPAuthenticator(efCredentials.getEmail(), efCredentials.getAppPassword());
    }
}
