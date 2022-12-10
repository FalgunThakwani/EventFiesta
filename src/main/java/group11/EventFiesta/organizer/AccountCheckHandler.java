package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.Organizer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountCheckHandler extends LoginHandler {

    IDBPersistence idbPersistence;

    AccountCheckHandler(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    @Override
    public LoginState execute(Account organizer) throws Exception {
        Object [] params = new Object[] {"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        Integer organizer_id;
        if (data.size() > 0) {
            organizer_id = Integer.parseInt(data.get(0).get("organizer_id").toString());
            System.out.println(organizer_id);
            organizer.setAccountId(organizer_id);
            return nextHandler.execute(organizer);
        } else {
            return new InvalidAccount();
        }
    }
}
