package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.SMTPProtocol;
import group11.EventFiesta.model.Account;

import java.security.SecureRandom;
import java.util.Random;


public class OTPHandler extends LoginHandler {

    IDBPersistence idbPersistence;
    SMTPProtocol SMTPProtocol;

    public OTPHandler(IDBPersistence idbPersistence, SMTPProtocol SMTPProtocol) {
        this.idbPersistence = idbPersistence;
        this.SMTPProtocol = SMTPProtocol;
    }

    @Override
    public LoginState execute(Account organizer) {
        Integer otp = generateOTP();
        saveOTP(organizer.getAccountId(), otp);
        Boolean mailSentStatus = sendMail(organizer.getEmail(), otp);
        if(mailSentStatus) {
            return new SentOTP();
        } else {
            return new MailNotSent();
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
            idbPersistence.saveData("update OrganizerSensitive set otp = " + otp + ", otp_time = " + otpTime + " where organizer_id= " + accountId);
        } catch (Exception exception) {
            System.out.println("Exception in otphandler: " + exception.getMessage());
        }
    }

    private boolean sendMail(String recipent, Integer otp) {
        String mailSubject = "Event Fiesta - Reset Password";
        String mailBody = "The OTP to reset password is: " + otp;
        Mail mail = new Mail(recipent, mailSubject, mailBody);
        return mail.sendMail(SMTPProtocol);
    }
}
