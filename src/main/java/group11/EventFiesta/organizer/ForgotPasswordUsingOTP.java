package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class ForgotPasswordUsingOTP implements IForgotPassword {

    IDBPersistence idbPersistence;

    public ForgotPasswordUsingOTP(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }
    public LoginState validate(Account organizer) {
        try {
            Integer otp = organizer.getOtp();
            System.out.println(otp);
            Object[] params = new Object[]{"OrganizerSensitive", "otp, otp_time", "organizer_id", organizer.getAccountId().toString()};
            ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
            System.out.println(data);
            if (data.size() > 0) {
                Integer originalOTP = Integer.parseInt(data.get(0).get("otp").toString());
                Long otpTime = Long.parseLong(data.get(0).get("otp_time").toString());
                Long fiveMinutesInMillis = 5 * 60 * 1000L;
                if (originalOTP.equals(otp) && otpTime > System.currentTimeMillis() - fiveMinutesInMillis) {
                    return new ValidatedOTP();
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in validateOTP() : " + ex.getMessage());
        }
        return new IncorrectOTP();
    }
}
