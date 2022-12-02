package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.model.Organizer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class VerifyPasswordHandler extends LoginHandler {

    @Override
    public boolean execute(Organizer organizer, HttpServletRequest request ) throws Exception {

        IDBPersistence idbPersistence = new MySQLDBPersistence();
        Object [] params = new Object[] {"BusinessSensitive", "encrypted_password, private_key", "business_id", organizer.getOrganizerId()};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        if (data.size() > 0) {
            HashMap<String, Object> row = data.get(0);
            String pwdFromDB = row.get("encrypted_password").toString();
            String saltFromDB = row.get("private_key").toString();
            String encPwd = EncryptPassword.getEncryptedPwd(organizer.getPassword(), saltFromDB);
            if (pwdFromDB.equals(encPwd)) {
                return nextHandler.execute(organizer, request);
            }
        }
        return false;
    }
}
