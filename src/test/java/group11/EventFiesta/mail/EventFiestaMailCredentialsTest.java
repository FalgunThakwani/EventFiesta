package group11.EventFiesta.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventFiestaMailCredentialsTest {

    @Test
    public void getterTest() {
        EventFiestaMailCredentials credential = new EventFiestaMailCredentials();
        Assertions.assertEquals("event.fiesta.ca@gmail.com", credential.getEmail());
        Assertions.assertEquals("iptcqvxxzfyrogta", credential.getAppPassword());
    }
}
