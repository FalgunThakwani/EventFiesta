package group11.EventFiesta.user;

import java.util.ArrayList;
import java.util.HashMap;

import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.ISignup;
import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.User;

public class UserSignUp extends ISignup {
    private IDBPersistence connection;

    public UserSignUp(IDBPersistence conPersistence) {
        this.connection = conPersistence;
    }

    @Override
    public boolean validateUser(Account user) throws Exception {

        ArrayList<HashMap<String, Object>> resultSet = connection.loadData("sp_checkUserExists", user.getEmail());
        if (resultSet.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void storeInfo(Account object) {
        /// In user sign up the Account object is expected to be a User class object
        User user = (User) object;

    }

    private Object[] createParams(User user) {
        String key = EncryptPassword.getNextSalt();
        String encryptedPassword = encryptReceivedPassword(user.getPassword(), key);
        Object[] params = { user.getFirstName(), user.getLastName(), user.getEmail(), encryptedPassword };

        return params;
    }

    public String encryptReceivedPassword(String password, String key) {
        String encryptedPWD = EncryptPassword.getEncryptedPwd(password, key);
        return encryptedPWD;
    }

}
