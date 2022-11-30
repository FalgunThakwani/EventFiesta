package group11.EventFiesta.user;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.ILogin;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLogin implements ILogin {
    @Override
    public Object login(String email, String password) throws Exception {

        String query = "select * from event_fiesta.userinfo where email=" + email;
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
            }
        }


    }

    @Override
    public Boolean logout(Object object) {
        return null;
    }

    @Override
    public Boolean resetPassword(String emailId, String newPassword){
        return null;
    }
}
