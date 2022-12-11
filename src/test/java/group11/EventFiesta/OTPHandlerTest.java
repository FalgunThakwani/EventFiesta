package group11.EventFiesta;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.account.forgotpassword.otp.OTPHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OTPHandlerTest {
    @Test
    public void generateOTPTest() {
        IDBPersistence mockDBPersistence = new MySQLDBPersistence();
        OTPHandler otpHandler = new OTPHandler(mockDBPersistence);
        Account organizer = new Organizer();
        organizer.setAccountId(1);
        organizer.setEmail("sreedevi.rw@gmail.com");
        Assertions.assertEquals(otpHandler.execute(organizer).getStatus(), "OTP has been sent");
    }
}
