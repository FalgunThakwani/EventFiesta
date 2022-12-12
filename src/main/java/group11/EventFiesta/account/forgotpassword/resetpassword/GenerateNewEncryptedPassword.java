package group11.EventFiesta.account.forgotpassword.resetpassword;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.EncryptPassword;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerateNewEncryptedPassword {

    Object[] params;
    IDBPersistence idbPersistence;

    public GenerateNewEncryptedPassword(IDBPersistence idbPersistence, Object[] params) {
        this.idbPersistence = idbPersistence;
        this.params = params;
    }

    public String getEncryptedPassword(String newPassword) {
        ArrayList<HashMap<String, Object>> data = null;
        try {
            data = idbPersistence.loadData("getFromDBUsingWhere", params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(data);
        if (data.size() > 0) {
            HashMap<String, Object> row = data.get(0);
            String saltFromDB = row.get("private_key").toString();
            String newEncryptedPassword = EncryptPassword.getEncryptedPwd(newPassword, saltFromDB);
            return newEncryptedPassword;
        }
        return "FAILURE";
    }
}
