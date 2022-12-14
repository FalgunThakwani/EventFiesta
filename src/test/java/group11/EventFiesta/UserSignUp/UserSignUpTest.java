package group11.EventFiesta.UserSignUp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.User;
import group11.EventFiesta.signUp.ISignup;
import group11.EventFiesta.signUp.UserSignUp;

public class UserSignUpTest {
    private IDBPersistence dbPersistence = new MySQLDBPersistence();
    ISignup signupTest = new UserSignUp(dbPersistence);
    private Account user = new User();

    @Test
    public void validateUser() throws Exception {
        user.setEmail("falgunthakwani@gmail.com");
        Assertions.assertEquals(signupTest.validateUser(user), true);
    }

    @Test
    public void validateWrongUser() throws Exception {
        user.setEmail("fl700637@dal.ca");
        Assertions.assertEquals(signupTest.validateUser(user), false);
    }

    // @Test
    // public void storeInfo() throws Exception {
    // // signupTest.storeInfo(user);
    // }

}
