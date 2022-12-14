package group11.EventFiesta.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

    private String userName;
    private String password;

    public SMTPAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        if (userName != null && password != null) {
            return new PasswordAuthentication(userName, password);
        } else {
            return null;
        }
    }
}
