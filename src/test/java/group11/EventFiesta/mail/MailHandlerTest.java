package group11.EventFiesta.mail;

import org.junit.jupiter.api.Test;

public class MailHandlerTest {

    @Test
    public void sendMailtest() {
        MailHandler mailHandler = new SSLMailHandler("smtp.gmail.com", 465);
        Mail mail = new Mail("sreedevi.rw@gmail.com", "Event Fiesta test mail", "This is a test mail!");
        mail.sendMail(mailHandler);
    }
}
