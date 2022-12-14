package group11.EventFiesta.account.forgotpassword.resetpassword;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.security.EncryptData;

import java.util.List;
import java.util.Map;

public class GenerateNewEncryptedPassword {

    Object[] params;
    IDBPersistence idbPersistence;

    public GenerateNewEncryptedPassword(IDBPersistence idbPersistence, Object[] params) {
        this.idbPersistence = idbPersistence;
        this.params = params;
    }

    public String getEncryptedPassword(String newPassword) {
        List<Map<String, Object>> data = null;
        try {
            data = idbPersistence.loadData("getFromDBUsingWhere", params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(data);
        if (data.size() > 0) {
            Map<String, Object> row = data.get(0);
            String saltFromDB = row.get("private_key").toString();
            String newEncryptedPassword = EncryptData.encryptData(newPassword, saltFromDB);
            return newEncryptedPassword;
        }
        return "FAILURE";
    }
}
