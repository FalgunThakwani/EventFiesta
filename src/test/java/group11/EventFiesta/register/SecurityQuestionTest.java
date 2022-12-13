package group11.EventFiesta.register;

import group11.EventFiesta.account.forgotpassword.securityquestion.VerifyEmailHandler;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SecurityQuestionTest
{
    @Test
    public void validateEmailTest()
    {
        String email = "testuser@test.com";
        Object[] params1 = new Object[]{"UserInfo", "*", "email", email};
        SecurityPasswordMockDB mockDb = new SecurityPasswordMockDB();
        VerifyEmailHandler emailHandler = new VerifyEmailHandler(mockDb, params1);

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecretQuestion("question");
        user.setPassword("password");
        user.setEmail("testuser@test.com");
        user.setOrg(false);

        try
        {
            List<Map<String, Object>> data = emailHandler.validateEmail(user);
            Assertions.assertEquals(1, data.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void validateEmailNotMatchedTest()
    {
        String email = "unknown@test.com";
        Object[] params1 = new Object[]{"UserInfo", "*", "email", email};
        SecurityPasswordMockDB mockDb = new SecurityPasswordMockDB();
        VerifyEmailHandler emailHandler = new VerifyEmailHandler(mockDb, params1);

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecretQuestion("question");
        user.setPassword("password");
        user.setEmail("unknown@test.com");
        user.setOrg(false);

        try
        {
            List<Map<String, Object>> data = emailHandler.validateEmail(user);
            Assertions.assertNull(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
