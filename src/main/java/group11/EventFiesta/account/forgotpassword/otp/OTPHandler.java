package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.login.organizer.LoginHandler;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.SMTPProtocol;
import group11.EventFiesta.model.Account;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;


public class OTPHandler extends LoginHandler {

    IDBPersistence idbPersistence;
    SMTPProtocol SMTPProtocol;

    Object[] param;

    public OTPHandler(IDBPersistence idbPersistence, Object[] param, SMTPProtocol SMTPProtocol) {
        this.idbPersistence = idbPersistence;
        this.param = param;
        this.SMTPProtocol = SMTPProtocol;
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
            Object[] newParam = new Object[param.length + 3];
            int i = 0;
            for (; i < param.length; i++) {
                newParam[i] = param[i];
            }
            newParam[i++] = otp;
            newParam[i++] = otpTime;
            newParam[i] = accountId;
            System.out.println(Arrays.toString(newParam));
            idbPersistence.updateData("updateTwoColumnDBUsingWhere", newParam);//"update OrganizerSensitive set otp = " + otp + ", otp_time = " + otpTime + " where organizer_id= " + accountId); //todo move to stored procedure
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
