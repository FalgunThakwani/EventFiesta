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

    public Mail(String recipent, String subject, String body) {
        this.recipent = recipent;
        this.subject = subject;
        this.body = body;
        this.from = eventFiestaMailCredentials.getEmail();
    }

    public Boolean sendMail(MailProtocol mailProtocol) {
        System.out.println("Inside send mail");
        Boolean status = true;
        Properties properties = mailProtocol.getMailProperties();
        Session session = Session.getInstance(properties, mailProtocol.getAuthenticator(eventFiestaMailCredentials));
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            status = false;
        }
        return status;
    }

}
