package group11.EventFiesta.mail;

import javax.mail.Authenticator;
import java.util.Properties;

public abstract class MailProtocol {

    String host;

    Integer port;

    Integer timeout = 3000;

    public MailProtocol(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public MailProtocol(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public abstract Properties getMailProperties();

    public abstract Authenticator getAuthenticator(EventFiestaMailCredentials eventFiestaMailCredentials);
}
