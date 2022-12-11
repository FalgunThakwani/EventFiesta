package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.login.organizer.LoginHandler;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.MailHandler;
import group11.EventFiesta.mail.SSLMailHandler;
import group11.EventFiesta.model.Account;

import java.security.SecureRandom;
import java.util.Random;


public class OTPHandler extends LoginHandler {

    IDBPersistence idbPersistence;

    public OTPHandler(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    @Override
    public IState execute(Account account) {
        Integer otp = generateOTP();
        saveOTP(account.getAccountId(), otp);
        Boolean mailSentStatus = sendMail(account.getEmail(), otp);
        if(mailSentStatus) {
            return new SentOTP(account);
        } else {
            return new MailNotSent(account);
        }
    }

    private Integer generateOTP() {
        Random random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            otp.append(random.nextInt(10));
        }
        return Integer.parseInt(otp.toString());
    }

    private void saveOTP(Integer accountId, Integer otp) {
        try {
            Long otpTime = System.currentTimeMillis();
            idbPersistence.saveData("update OrganizerSensitive set otp = " + otp + ", otp_time = " + otpTime + " where organizer_id= " + accountId); //todo move to stored procedure
        } catch (Exception exception) {
            System.out.println("Exception in otphandler: " + exception.getMessage());
        }
    }

    private boolean sendMail(String recipent, Integer otp) {
        String mailSubject = "Event Fiesta - Reset Password";
        String mailBody = "The OTP to reset password is: " + otp;
        Mail mail = new Mail(recipent, mailSubject, mailBody);
        MailHandler mailHandler = new SSLMailHandler("smtp.gmail.com", 465);
        return mail.sendMail(mailHandler);
    }
}
