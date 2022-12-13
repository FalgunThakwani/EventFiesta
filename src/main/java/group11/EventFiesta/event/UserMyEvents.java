package group11.EventFiesta.event;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.model.UserEvents;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class UserMyEvents implements IEvents{
    private IDBPersistence connection;
    public UserMyEvents(IDBPersistence conPersistence){
        this.connection = conPersistence;
    }
    @Override
    public ArrayList<UserEvents> loadEvents(int userId) throws Exception {
        Object[] params = createParams(userId);
        ArrayList<HashMap<String, Object>> resultSet = connection.loadData("sp_getUserEvents", params);
        ArrayList<UserEvents> eventsList = new ArrayList<UserEvents>();
        for(int i=0;i< resultSet.size(); i++){
            HashMap<String, Object> row = resultSet.get(i);
            int columns = row.size();
            System.out.println(row);
            UserEvents event = new UserEvents();
            event.setUserId(userId);
            event.setEventId((int)row.get("event_id"));
            event.setEventType((String)row.get("type"));
            event.setVenue((String)row.get("venue"));
            event.setEventDate((LocalDateTime)row.get("event_date"));
            event.setTotalCost((int)row.get("total_cost"));
            event.setHeadCount((int)row.get("head_count"));
            event.setServiceId((int)row.get("service_id"));
            event.setServiceType((String)row.get("service_type"));
            event.setServiceCost((int)row.get("cost"));
            event.setStatus((String)row.get("status"));
            eventsList.add(event);
        }
        return eventsList;
    }

    private Object[] createParams(int userId) {
        Object[] params = {userId};
        System.out.println(params.length);
        return params;
    }
}
