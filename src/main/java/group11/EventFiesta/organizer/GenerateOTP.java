package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.mail.SMTPProtocol;
import group11.EventFiesta.mail.SSLSMTPProtocol;
import group11.EventFiesta.model.Account;

public class GenerateOTP {

    public LoginState generateOTP(Account organizer) {
        try {
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence);
            SMTPProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            ILoginHandler otpHandler = new OTPHandler(mySQLDBPersistence, gmailSslSmtpProtocol);
            accounCheckHandler.setNextHandler(otpHandler);
            return accounCheckHandler.execute(organizer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
