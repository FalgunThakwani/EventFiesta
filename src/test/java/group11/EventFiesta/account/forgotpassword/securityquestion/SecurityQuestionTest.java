package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SecurityQuestionTest
{
    @Test
    public void validateEmailTest()
    {
        String email = "testuser@test.com";
        Object[] params1 = new Object[]{"UserInfo", "*", "email", email};
        IDBPersistence mockDb = new MockSecurityPassword();
        VerifyEmailHandler emailHandler = new VerifyEmailHandler(mockDb, params1);

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("testuser@test.com");
        user.setIsOrganizer(false);

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
        IDBPersistence mockDb = new MockSecurityPassword();
        VerifyEmailHandler emailHandler = new VerifyEmailHandler(mockDb, params1);

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("unknown@test.com");
        user.setIsOrganizer(false);

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

    @Test
    public void validateSecurityCredentialsTest()
    {
        IDBPersistence mockDb = new MockSecurityPassword();
        Object[] params = new Object[]{"UserSensitive", "*", "user_id", 1};

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("unknown@test.com");
        user.setIsOrganizer(false);

        SecretQuestionHandler secretQuestionHandler = new SecretQuestionHandler(mockDb, params, "question","answer");
        IState state = secretQuestionHandler.validate(user);

        Assertions.assertEquals("Security Details validated successfully!", state.getStatus());
    }

    @Test
    public void validateSecurityCredentialsWrongQuestionTest()
    {
        IDBPersistence mockDb = new MockSecurityPassword();
        Object[] params = new Object[]{"UserSensitive", "*", "user_id", 1};

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("unknown@test.com");
        user.setIsOrganizer(false);

        SecretQuestionHandler secretQuestionHandler = new SecretQuestionHandler(mockDb, params, "wrong","answer");
        IState state = secretQuestionHandler.validate(user);

        Assertions.assertEquals("Security Details not validated successfully!", state.getStatus());
    }

    @Test
    public void validateSecurityCredentialsWrongAnswerTest()
    {
        IDBPersistence mockDb = new MockSecurityPassword();
        Object[] params = new Object[]{"UserSensitive", "*", "user_id", 1};

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("unknown@test.com");
        user.setIsOrganizer(false);

        SecretQuestionHandler secretQuestionHandler = new SecretQuestionHandler(mockDb, params, "question","wrong");
        IState state = secretQuestionHandler.validate(user);

        Assertions.assertEquals("Security Details not validated successfully!", state.getStatus());
    }

    @Test
    public void validateSecurityCredentialsWrongQuestionAndAnswerTest()
    {
        IDBPersistence mockDb = new MockSecurityPassword();
        Object[] params = new Object[]{"UserSensitive", "*", "user_id", 1};

        User user = new User();
        user.setUserId(1);
        user.setFirstName("user");
        user.setLastName("name");
        user.setSecurityQuestion("question");
        user.setSecurityAnswer("answer");
        user.setPassword("password");
        user.setEmail("unknown@test.com");
        user.setIsOrganizer(false);

        SecretQuestionHandler secretQuestionHandler = new SecretQuestionHandler(mockDb, params, "wrongquestion","wronganswer");
        IState state = secretQuestionHandler.validate(user);

        Assertions.assertEquals("Security Details not validated successfully!", state.getStatus());
    }
}
