package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.model.Account;

import java.util.List;
import java.util.Map;

public class ForgotPasswordUsingOTP implements IForgotPassword {

    Object[] params;
    IDBPersistence idbPersistence;

    public ForgotPasswordUsingOTP(IDBPersistence idbPersistence, Object [] params) {
        this.idbPersistence = idbPersistence;
        this.params = params;
    }
    public IState validate(Account account) {
        try {
            Integer otp = account.getOtp();
            List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
            if (data.size() > 0) {
                Integer originalOTP = Integer.parseInt(data.get(0).get("otp").toString());
                Long otpTime = Long.parseLong(data.get(0).get("otp_time").toString());
                if (originalOTP.equals(otp)) {
                    if(validateOTPTime(otpTime)) {
                        return new ValidatedOTP(account);
                    } else {
                        return new OTPExpired(account);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in validateOTP() : " + ex.getMessage());
        }
        return new IncorrectOTP(account);
    }

    private boolean validateOTPTime(Long otpTime) {
        Long currentTime = System.currentTimeMillis();
        Long fiveMinutesInMillis = 5 * 60 * 1000L;
        Long otpTimeLowerThreshold = currentTime - fiveMinutesInMillis;
        if (otpTime > otpTimeLowerThreshold) {
            return true;
        } else {
            return false;
        }
    }
}
