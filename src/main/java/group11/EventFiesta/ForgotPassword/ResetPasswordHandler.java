package group11.EventFiesta.ForgotPassword;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.EncryptPassword;

import java.util.ArrayList;
import java.util.HashMap;

public class ResetPasswordHandler {

    IDBPersistence idbPersistence;

    public ResetPasswordHandler() {
        idbPersistence = new MySQLDBPersistence();
    }

    public boolean resetPassword(int userId, String newPassword) throws Exception {
            Object[] params = new Object[]{"UserSensitive", "private_key", "user_id", userId};
            ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
            System.out.println(data);
            if (data.size() > 0) {
                HashMap<String, Object> row = data.get(0);
                String saltFromDB = row.get("private_key").toString();
                String newEncryptedPassword = EncryptPassword.getEncryptedPwd(newPassword, saltFromDB);
                int result = idbPersistence.updateData("updateDBUsingWhere", "UserSensitive", "encrypted_password", newEncryptedPassword, "user_id", userId);
                System.out.println("Updated Row Count: " + result);
                if (result == 1) {
                    return true;
                }
                else {return false;
                }
            }
            else {
                return false;
            }
    }
}

