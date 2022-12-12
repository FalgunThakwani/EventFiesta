package group11.EventFiesta.mail;

import org.junit.jupiter.api.Test;

public class SMTPProtocolTest {

    @Test
    public void sendMailtest() {
        SMTPProtocol gmailSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
        Mail mail = new Mail("sreedevi.rw@gmail.com", "Event Fiesta test mail", "This is a test mail!");
//        mail.sendMail(gmailSmtpProtocol);
    }
}
