package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
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
            System.out.println(otp);
            List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
            System.out.println(data);
            if (data.size() > 0) {
                Integer originalOTP = Integer.parseInt(data.get(0).get("otp").toString());
                Long otpTime = Long.parseLong(data.get(0).get("otp_time").toString());
                Long fiveMinutesInMillis = 5 * 60 * 1000L;
                if (originalOTP.equals(otp) && otpTime > System.currentTimeMillis() - fiveMinutesInMillis) {
                    return new ValidatedOTP(account);
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in validateOTP() : " + ex.getMessage());
        }
        return new IncorrectOTP(account);
    }
}
