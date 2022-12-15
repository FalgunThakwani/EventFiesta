package group11.EventFiesta.UserSignUp;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockUserSignUpDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.User;
import group11.EventFiesta.signUp.ISignup;
import group11.EventFiesta.signUp.UserSignUp;

public class UserSignUpTest {
    private IDBPersistence dbPersistence = new MockUserSignUpDBPersistence();
    ISignup signupTest = new UserSignUp(dbPersistence);
    private User user = new User();

    @Test
    public void validateUser() throws Exception {
        List<Map<String, Object>> resultSet = dbPersistence.loadData("sp_checkUserExists", "falgunthakwani@gmail.com");
        Assertions.assertFalse(resultSet.size() > 0);
    }

    @Test
    public void validateWrongUser() throws Exception {
        user.setEmail("fl700637@dal.ca");
        List<Map<String, Object>> resultSet = dbPersistence.loadData("sp_checkUserExists", user.getEmail());
        Assertions.assertTrue(resultSet.size() == 0);
    }

    @Test
    public void storeInfo() throws Exception {
        Object[] params = { user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getSecurityQuestion(), user.getSecurityAnswer(), "2022-01-30", "2022-01-30",
                "Awqedm32fsdf13245", "vdskmk3324", "2022-02-15", 0 };
        List<Map<String, Object>> resultSet = dbPersistence.loadData("sp_storeUserData", params);
        Assertions.assertTrue(resultSet.size() > 0);
    }

}
