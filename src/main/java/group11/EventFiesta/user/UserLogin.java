package group11.EventFiesta.user;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.ILogin;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLogin implements ILogin {
    @Override
    public LoginState login(Account user, HttpServletRequest request) {
        try {
            IDBPersistence mySQLDBPersistence = new MySQLDBPersistence();
            ILoginHandler accounCheckHandler = new AccountCheckHandler(mySQLDBPersistence);
            ILoginHandler verifyPasswordHandler = new VerifyPasswordHandler(mySQLDBPersistence);
            ILoginHandler createSessionHandler = new CreateSessionHandler();
            accounCheckHandler.setNextHandler(verifyPasswordHandler);
            verifyPasswordHandler.setNextHandler(createSessionHandler);
            return accounCheckHandler.execute(organizer, request);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
        /* String query = "select * from event_fiesta.userinfo where email=" + email;
        IDBPersistence db = new MySQLDBPersistence();
        ArrayList<HashMap<String, Object>> result;
        result = db.loadData(query);
        if(result == null)
            return "UNKNOWN_USER";
        else
        {
            String user_id = String.valueOf(result.get(1));
            String saltFromDBQuery = "select private_key from event_fiesta.usersensitive where user_id=" + user_id;
            result =db.loadData(saltFromDBQuery);
            String saltFromDB = String.valueOf(result.get(1));
            String userEncryptedPassword = EncryptPassword.getEncryptedPwd(password, saltFromDB);
            String userEncryptedPasswordFromDBQuery = "select encrypted_password from event_fiesta.usersensitive where user_id=" + user_id;
            result =db.loadData(saltFromDBQuery);
            String userEncryptedPasswordFromDB = String.valueOf(result.get(1));
            if (userEncryptedPassword.equals(userEncryptedPasswordFromDB)) {
                return "SUCCESS";
            }
            else {
                return "WRONG PASSWORD";
            } */
        }


    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return true;
    }

    @Override
    public Boolean resetPassword(Account user)
    {
        return false;
    }
}
