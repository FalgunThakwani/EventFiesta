package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.UserEvent;
import group11.EventFiesta.model.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserMyEventDetails {

    IDBPersistence idbPersistence;
    public UserMyEventDetails(IDBPersistence dbPersistence) {
        this.idbPersistence = dbPersistence;
    }

    public UserEvent getUserMyEventDetails(int event_id) throws Exception {
        Object[] params = new Object[]{event_id};
        List<Map<String, Object>> data = idbPersistence.loadData("getUserMyEventDetails", params);

        List<UserService> userServiceList = new ArrayList<>();
        UserEvent userEvent = new UserEvent();

        if (data.size() > 0)
        {
            for (int i = 0; i < data.size(); i++)
            {
                Map<String, Object> row = data.get(i);

                userEvent.setEventID((Integer) row.get("event_id"));
                userEvent.setEventType((String) row.get("type"));
                userEvent.setEventVenue((String) row.get("venue"));
                userEvent.setGuestCount((Integer) row.get("head_count"));

                UserService service = new UserService();
                service.setId((Integer) row.get("service_id"));
                service.setType((String) row.get("service_type"));
                //service.setCost((String.valueOf(row.get("cost"))));

                Organizer organizer = new Organizer();
                organizer.setOrganizerId((Integer) row.get("organizer_id"));
                organizer.setFirstName((String) row.get("first_name"));
                organizer.setLastName((String) row.get("last_name"));
                organizer.setEmail((String) row.get("email"));

                service.setOrganizer(organizer);
                userServiceList.add(service);
            }

            userEvent.setService(userServiceList);
        }


        return userEvent;
    }
}
