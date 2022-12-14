package group11.EventFiesta.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@SpringBootTest
public class SSLSMTPProtocolTest {

    @Test
    public void getMailPropertiesSuccessTest() {
        String host = "testHost";
        int port = 1000;
        int defaultTimeOut = 3000;
        MailProtocol smtpProtocol = new SSLSMTPProtocol(host, port);
        Properties properties = smtpProtocol.getMailProperties();
        Assertions.assertEquals(host, properties.getProperty("mail.smtp.host"));
        Assertions.assertEquals(port, properties.get("mail.smtp.port"));
        Assertions.assertEquals(defaultTimeOut, properties.get("mail.smtp.timeout"));
        Assertions.assertEquals(port, properties.get("mail.smtp.socketFactory.port"));
        Assertions.assertEquals("javax.net.ssl.SSLSocketFactory", properties.get("mail.smtp.socketFactory.class"));
        Assertions.assertEquals("false", properties.get("mail.smtp.socketFactory.fallback"));
        Assertions.assertEquals("true", properties.get("mail.smtp.auth"));
        Assertions.assertEquals("true", properties.get("mail.smtp.starttls.enable"));
        Assertions.assertEquals("true", properties.get("mail.smtp.starttls.required"));
        Assertions.assertEquals("TLSv1.2", properties.get("mail.smtp.ssl.protocols"));
    }
}
