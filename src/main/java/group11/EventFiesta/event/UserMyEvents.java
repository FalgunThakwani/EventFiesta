package group11.EventFiesta.event;

//import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Service;
import group11.EventFiesta.model.UserEvents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMyEvents implements IEvents{
    private IDBPersistence connection;
    public UserMyEvents(IDBPersistence conPersistence){
        this.connection = conPersistence;
    }
    @Override
    public ArrayList<UserEvents> loadEvents(int userId) throws Exception {
        Object[] params = createParams(userId);
        List<Map<String, Object>> resultSet = connection.loadData("sp_getUserEvents", params);
        ArrayList<UserEvents> eventsList = new ArrayList<UserEvents>();
        HashMap<Integer, UserEvents> events = new HashMap<>();
        for(int i=0;i< resultSet.size(); i++){
            Map<String, Object> row = resultSet.get(i);
            UserEvents currentEvent;
            if(events.containsKey((int)row.get("event_id"))){
                currentEvent = events.get((int)row.get("event_id"));
            }else{
                currentEvent = new UserEvents();
                currentEvent.setUserId(userId);
                currentEvent.setEventId((int)row.get("event_id"));
                currentEvent.setEventType((String)row.get("type"));
                currentEvent.setVenue((String)row.get("venue"));
                currentEvent.setEventDate((LocalDateTime)row.get("event_date"));
                currentEvent.setTotalCost((int)row.get("total_cost"));
                currentEvent.setHeadCount((int)row.get("head_count"));
            }
            Service service = new Service();
            service.setServiceId((int)row.get("service_id"));
            service.setServiceType((String)row.get("service_type"));
            service.setCost((int)row.get("cost"));
            service.setStatus((String)row.get("status"));
            currentEvent.addService(service);

            events.put((int)row.get("event_id"), currentEvent);
        }
        for(UserEvents event: events.values()){
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
