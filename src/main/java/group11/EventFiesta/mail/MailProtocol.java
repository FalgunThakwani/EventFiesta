package group11.EventFiesta.mail;

import java.util.Properties;

public abstract class MailProtocol {

    String host;

    Integer port;

    Integer timeout = 3000;

    public MailProtocol(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public MailProtocol(String host, Integer port, Integer timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public abstract Properties getMailProperties();
}
