package group11.EventFiesta.account.login.user;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class VerifyPasswordHandler extends LoginHandler {

    IDBPersistence idbPersistence;

    VerifyPasswordHandler(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    @Override
    public LoginState execute(Account user, HttpServletRequest request) throws Exception {
        Object [] params = new Object[] {"UserSensitive", "encrypted_password, private_key", "user_id", user.getAccountId()};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        if (data.size() > 0) {
            HashMap<String, Object> row = data.get(0);
            String pwdFromDB = row.get("encrypted_password").toString();
            String saltFromDB = row.get("private_key").toString();
            String encPwd = EncryptPassword.getEncryptedPwd(user.getPassword(), saltFromDB);
            //String encPwd = "lvpEQgg0Woy8l5Wdu0JcZA==";
            if (pwdFromDB.equals(encPwd)) {
                return nextHandler.execute(user, request);
            }
        }
        return new IncorrectPassword();
    }

}
