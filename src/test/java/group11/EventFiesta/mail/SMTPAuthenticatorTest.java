package group11.EventFiesta.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SMTPAuthenticatorTest {

    @Test
    public void getAuthenticatorSuccessTest() {
        String userName = "test@gmail.com";
        String password = "testPassword";
        SMTPAuthenticator authenticator = new SMTPAuthenticator(userName, password);
        Assertions.assertNotNull(authenticator.getPasswordAuthentication());
    }

    @Test
    public void getAuthenticatorNullUsernameTest() {
        String userName = null;
        String password = "testPassword";
        SMTPAuthenticator authenticator = new SMTPAuthenticator(userName, password);
        Assertions.assertNull(authenticator.getPasswordAuthentication());
    }

    @Test
    public void getAuthenticatorNullPasswordTest() {
        String userName = "test@gmail.com";
        String password = null;
        SMTPAuthenticator authenticator = new SMTPAuthenticator(userName, password);
        Assertions.assertNull(authenticator.getPasswordAuthentication());
    }
}
