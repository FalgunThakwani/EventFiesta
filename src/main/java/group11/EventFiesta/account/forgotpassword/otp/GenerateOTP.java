package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.login.organizer.AccountCheckHandler;
import group11.EventFiesta.account.login.organizer.ILoginHandler;
import group11.EventFiesta.mail.SMTPProtocol;
import group11.EventFiesta.mail.SSLSMTPProtocol;
import group11.EventFiesta.model.Account;

import java.util.List;

public class GenerateOTP {

    IDBPersistence idbPersistence;
    public GenerateOTP(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    public IState generateOTP(Account account, List<Object []> params) {
        try {
            System.out.println("Inside generateOTP: ");
            System.out.println(account);
            ILoginHandler accounCheckHandler = new AccountCheckHandler(idbPersistence, params.get(0));
            SMTPProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            ILoginHandler otpHandler = new OTPHandler(idbPersistence, params.get(1), gmailSslSmtpProtocol);
            accounCheckHandler.setNextHandler(otpHandler);
            return accounCheckHandler.execute(account);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
