package group11.EventFiesta.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Mail {

    static Properties eventFiestaMailCredentials;

    static {
        try {
            eventFiestaMailCredentials = new Properties();
            InputStream applicationProperties = Mail.class.getClassLoader().getResourceAsStream("application.properties");
            eventFiestaMailCredentials.load(applicationProperties);
            System.out.println(eventFiestaMailCredentials);
        } catch (IOException exception) {
            System.out.println("Exteption while loading mail credentials: " + exception.getMessage());
        }
    }

    private String recipent;

    private String subject;

    private String body;

    private String from;

    private Authenticator authenticator;
    public Mail(String recipent, String subject, String body) {
        this.recipent = recipent;
        this.subject = subject;
        this.body = body;
        this.from = eventFiestaMailCredentials.getProperty("event_fiesta.default_email.email");
        setAuthentication();
    }

    public void setAuthentication() {
        authenticator = new SMTPAuthenticator(eventFiestaMailCredentials.getProperty("event_fiesta.default_email.email"),
                eventFiestaMailCredentials.getProperty("event_fiesta.default_email.app_password"));

    }
    public String getRecipent() {
        return recipent;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getFrom() {
        return from;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public Boolean sendMail(SMTPProtocol SMTPProtocol) {
        Boolean status = true;
        Properties properties = SMTPProtocol.getMailProperties();
        Session session = Session.getInstance(properties, getAuthenticator());
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getRecipent()));
            message.setSubject(getSubject());
            message.setText(getBody());
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            status = false;
        }
        return status;
    }
}
