package group11.EventFiesta.account.login.user;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public class UserLoginTest {

    @Test
    public void userLoginFailInvalidAccountTest() {
        try {
            HttpServletRequest httpServletRequest = new MockHttpServletRequest();
            IDBPersistence mockDbPersistence = new MockUserLogin();

            User user = new User();
            user.setEmail("ruchika@gmail.com");
            user.setPassword("1234");
            Object[] params = new Object[]{"UserInfo", "user_id", "email", user.getEmail()};

            ILoginHandler accountCheckHandler = new AccountCheckHandler(mockDbPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mockDbPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(httpServletRequest);
            accountCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            IState state = accountCheckHandler.execute(user);

            Assertions.assertEquals(state.getStatus(), "Invalid Account !! Please Try With Valid Account");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void userLoginPassTest() {
        try {
            HttpServletRequest httpServletRequest = new MockHttpServletRequest();
            IDBPersistence mockDbPersistence = new MockUserLogin();

            User user = new User();
            user.setEmail("test2@gmail.com");
            user.setPassword("1234");
            Object[] params = new Object[]{"UserInfo", "user_id", "email", user.getEmail()};

            ILoginHandler accountCheckHandler = new AccountCheckHandler(mockDbPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mockDbPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(httpServletRequest);
            accountCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            IState state = accountCheckHandler.execute(user);

            Assertions.assertEquals(state.getStatus(), "Successfully Logged In !!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void userLoginFailWrongPasswordTest() {
        try {
            HttpServletRequest httpServletRequest = new MockHttpServletRequest();
            IDBPersistence mockDbPersistence = new MockUserLogin();

            User user = new User();
            user.setEmail("test2@gmail.com");
            user.setPassword("123456");
            Object[] params = new Object[]{"UserInfo", "user_id", "email", user.getEmail()};

            ILoginHandler accountCheckHandler = new AccountCheckHandler(mockDbPersistence, params);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mockDbPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler(httpServletRequest);
            accountCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            IState state = accountCheckHandler.execute(user);

            Assertions.assertEquals(state.getStatus(), "Password Incorrect !! Please enter Correct Password.");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
