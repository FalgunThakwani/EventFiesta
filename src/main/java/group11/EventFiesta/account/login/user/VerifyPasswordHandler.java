package group11.EventFiesta.account.login.user;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.security.EncryptData;
import group11.EventFiesta.model.Account;

import java.util.List;
import java.util.Map;

public class VerifyPasswordHandler extends LoginHandler {

    IDBPersistence idbPersistence;

    VerifyPasswordHandler(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    @Override
    public IState execute(Account user) throws Exception {
        Object [] params = new Object[] {"UserSensitive", "encrypted_password, private_key", "user_id", user.getAccountId()};
        List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        if (data.size() > 0) {
            Map<String, Object> row = data.get(0);
            String pwdFromDB = row.get("encrypted_password").toString();
            String saltFromDB = row.get("private_key").toString();
            String encPwd = EncryptData.encryptData(user.getPassword(), saltFromDB);
            if (pwdFromDB.equals(encPwd)) {
                return nextHandler.execute(user);
            }
        }
        return new IncorrectPassword(user);
    }

}
