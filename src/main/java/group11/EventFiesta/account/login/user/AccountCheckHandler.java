package group11.EventFiesta.account.login.user;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.model.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountCheckHandler extends LoginHandler {

    IDBPersistence idbPersistence;

    public AccountCheckHandler(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    @Override
    public LoginState execute(Account user, HttpServletRequest request) throws Exception {
        Object [] params = new Object[] {"UserInfo", "user_id", "email", user.getEmail()};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        Integer user_id = -1;
        if (data.size() > 0) {
            user_id = Integer.parseInt(data.get(0).get("user_id").toString());
            System.out.println(user_id);
            user.setAccountId(user_id);
            return nextHandler.execute(user, request);
        } else {
            return new InvalidAccount();
        }
    }
}
