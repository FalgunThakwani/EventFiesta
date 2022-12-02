package group11.EventFiesta.organizer;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Organizer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountCheckHandler extends LoginHandler {

    @Override
    public boolean execute(Organizer organizer, HttpServletRequest request) throws Exception {
//        IDBPersistence idbPersistence = new MySQLDBPersistence();
//        String query = "select organizer_id from OrganizerInfo where email = \'" + organizer.getEmail() + "\';";
//        System.out.println(query);
//        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData(query);
//        System.out.println(data);
//        Integer organizer_id = -1;
//        if (data.size() > 0) {
//            organizer_id = Integer.parseInt(data.get(0).get("organizer_id").toString());
//            System.out.println(organizer_id);
//            organizer.setOrganizerId(organizer_id);
//            return nextHandler.execute(organizer, request);
//        } else {
//            return false;
//        }

        /* stored procedure */
        IDBPersistence idbPersistence = new MySQLDBPersistence();
        Object [] params = new Object[] {"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
        ArrayList<HashMap<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        System.out.println(data);
        Integer organizer_id = -1;
        if (data.size() > 0) {
            organizer_id = Integer.parseInt(data.get(0).get("organizer_id").toString());
            System.out.println(organizer_id);
            organizer.setOrganizerId(organizer_id);
            return nextHandler.execute(organizer, request);
        } else {
            return false;
        }
    }
}
