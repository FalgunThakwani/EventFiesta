package group11.EventFiesta.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    private static EventFiestaMailCredentials eventFiestaMailCredentials = new EventFiestaMailCredentials();
    private String recipent;

    private String subject;

    private String body;

    private String from;

    private Authenticator authenticator;
    public Mail(String recipent, String subject, String body) {
        this.recipent = recipent;
        this.subject = subject;
        this.body = body;
        this.from = eventFiestaMailCredentials.getEmail();
        setAuthentication();
    }

    public Mail(String subject, String body) {
        this.subject = subject;
        this.body = body;
        this.from = eventFiestaMailCredentials.getEmail();
        setAuthentication();
    }

    public void setAuthentication() {
        authenticator = new SMTPAuthenticator(eventFiestaMailCredentials.getEmail(), eventFiestaMailCredentials.getAppPassword());

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

    public void setRecipent(String recipent) {
        this.recipent = recipent;
    }

    public Boolean sendMail(MailProtocol mailProtocol) {
        Boolean status = true;
        Properties properties = mailProtocol.getMailProperties();
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
