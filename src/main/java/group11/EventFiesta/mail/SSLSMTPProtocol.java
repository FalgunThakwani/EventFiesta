package group11.EventFiesta.mail;

import java.util.Properties;

public class SSLSMTPProtocol extends SMTPProtocol {

    public SSLSMTPProtocol(String host, int port) {
        super(host, port);
    }

    public Properties getMailProperties() {
        Properties properties = super.getMailProperties();
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }
}
