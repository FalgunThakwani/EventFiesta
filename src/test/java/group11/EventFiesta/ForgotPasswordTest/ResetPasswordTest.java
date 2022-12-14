package group11.EventFiesta.ForgotPasswordTest;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.ResetPasswordHandler;
import group11.EventFiesta.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResetPasswordTest {

    @Test
    public void validateResetPasswordPassTest() {
        MockResetPassword mockDB = new MockResetPassword();
        Object[] params2 = new Object[]{"UserSensitive", "encrypted_password", "[64, 82, -28, 9, -41, -67, -60, 81, -48, 86, 91, -32, 110, 28, -1, 73]", "user_id", 1};
        IForgotPassword resetPasswordHandler = new ResetPasswordHandler(mockDB, params2);

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setEmail("test@test.com");

        IState state = resetPasswordHandler.validate(user);
        Assertions.assertEquals("PASSWORD UPDATED SUCCESSFULLY !!", state.getStatus());
    }

    @Test
    public void validateResetPasswordFailTest() {
        MockResetPassword mockDB = new MockResetPassword();
        Object[] params2 = new Object[]{"UserSensitive", "encrypted_password", "[64, 82, -28, 9, -41, -67, -60, 81, -48, 86, 91, -32, 110, 28, -1, 73]", "user_id", 3};
        IForgotPassword resetPasswordHandler = new ResetPasswordHandler(mockDB, params2);

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setEmail("test@test.com");

        IState state = resetPasswordHandler.validate(user);
        Assertions.assertEquals("PASSWORD NOT UPDATED SUCCESSFULLY !!", state.getStatus());
    }
}
