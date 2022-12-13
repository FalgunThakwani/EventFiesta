package group11.EventFiesta.account.forgotpassword.otp;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockOTPDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ForgotPasswordUsingOTPTest {

    private static IDBPersistence mockDBPersistence;

    static {
        mockDBPersistence = new MockOTPDBPersistence();
    }

    @Test
    public void validateUserOTPSuccessTest() {
        Account user = new User();
        user.setEmail("success@gmail.com");
        user.setAccountId(1);
        user.setOtp(1111);

        Object[] params = new Object[]{"UserSensitive", "otp, otp_time", "user_id", user.getAccountId().toString()};
        IForgotPassword mockForgotPwdUsingOTP = new ForgotPasswordUsingOTP(mockDBPersistence, params);
        IState state = mockForgotPwdUsingOTP.validate(user);

        Assertions.assertEquals("OTP validated succesfully!", state.getStatus());
        Assertions.assertEquals("userResetPassword", state.getNextPage());
    }

    @Test
    public void validateUserExpiredOTPTest() {
        Account user = new User();
        user.setEmail("expired@gmail.com");
        user.setAccountId(2);
        user.setOtp(1111);

        Object[] params = new Object[]{"UserSensitive", "otp, otp_time", "user_id", user.getAccountId().toString()};
        IForgotPassword mockForgotPwdUsingOTP = new ForgotPasswordUsingOTP(mockDBPersistence, params);
        IState state = mockForgotPwdUsingOTP.validate(user);

        Assertions.assertEquals("OTP expired!", state.getStatus());
        Assertions.assertEquals("redirect:/UserForgotPasswordOTP", state.getNextPage());
    }
    @Test
    public void validateOrganizerOTPSuccessTest() {
        Account organizer = new Organizer();
        organizer.setEmail("success@gmail.com");
        organizer.setAccountId(1);
        organizer.setOtp(1111);

        Object[] params = new Object[]{"OrganizerSensitive", "otp, otp_time", "organizer_id", organizer.getAccountId().toString()};
        IForgotPassword mockForgotPwdUsingOTP = new ForgotPasswordUsingOTP(mockDBPersistence, params);
        IState state = mockForgotPwdUsingOTP.validate(organizer);

        Assertions.assertEquals("OTP validated succesfully!", state.getStatus());
        Assertions.assertEquals("organizerResetPassword", state.getNextPage());
    }

    @Test
    public void validateOrganizerExpiredOTPTest() {
        Account organizer = new Organizer();
        organizer.setEmail("expired@gmail.com");
        organizer.setAccountId(2);
        organizer.setOtp(1111);

        Object[] params = new Object[]{"OrganizerSensitive", "otp, otp_time", "organizer_id", organizer.getAccountId().toString()};
        IForgotPassword mockForgotPwdUsingOTP = new ForgotPasswordUsingOTP(mockDBPersistence, params);
        IState state = mockForgotPwdUsingOTP.validate(organizer);

        Assertions.assertEquals("OTP expired!", state.getStatus());
        Assertions.assertEquals("redirect:/OrganizerForgotPasswordOTP", state.getNextPage());
    }
}
