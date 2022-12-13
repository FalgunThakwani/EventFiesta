package group11.EventFiesta.event;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.best5.GroupComponent;
import group11.EventFiesta.best5.OrganizerGroup;
import group11.EventFiesta.best5.OrganizerService;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.MailProtocol;
import group11.EventFiesta.model.UserEventQuestionnaire;

import java.sql.Types;
import java.util.List;
import java.util.Map;

public class EventManager {

    IDBPersistence idbPersistence;
    MailProtocol mailProtocol;
    public EventManager(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    public EventManager(IDBPersistence idbPersistence, MailProtocol protocol) {
        this.idbPersistence = idbPersistence;
        mailProtocol = protocol;
    }

    public void updateEvent(Object [] params, Mail mail) throws Exception {
        Integer rows = idbPersistence.updateData("updateEventStatus", params);
        if (rows >= 1) {
            mail.sendMail(mailProtocol);
        }
        System.out.println("Updated rows: " + rows);
    }

    public List<Map<String, Object>> getEventServices(Integer organizerId, String status) throws Exception {
        Object [] params = new Object[]{organizerId, status};
        List<Map<String, Object>> eventDetails = idbPersistence.loadData("getOrganizerEventDetails", params);
        for (Map<String, Object> event : eventDetails) {
            Integer eventId = Integer.parseInt(event.get("event_id").toString());
            params= new Object[]{eventId, status, organizerId};
            List<Map<String, Object>> serviceDetails = idbPersistence.loadData("getOrganizerEventServices", params);
            event.put("services", serviceDetails);
        }
        return eventDetails;
    }

    public void addEvent(UserEventQuestionnaire eventDetails, OrganizerGroup selectedGroup, Integer userId, Mail mail) throws Exception {
        String venue = eventDetails.getCity() + ", " + eventDetails.getProvince();
        Object[] params = new Object[]{userId, eventDetails.getEvent(), venue, eventDetails.getDateTime(),
                eventDetails.getBudget(), eventDetails.getGuestCount()};
        int[] outParams = new int[]{Types.INTEGER};
        List<Object> returnValues = idbPersistence.insertData("addEvent", params, outParams);
        System.out.println(returnValues);
        if (returnValues != null && returnValues.size() > 0) {
            Integer eventId = Integer.parseInt(returnValues.get(0).toString());
            for (GroupComponent organizerService : selectedGroup.getOrganizerServices()) {
                OrganizerService service = (OrganizerService) organizerService;
                String status = "Pending";
                params = new Object[]{eventId, service.getId(), service.getBudget(), status};
                outParams = new int[]{};
                returnValues = idbPersistence.insertData("addService", params, outParams);
                //mail.setRecipent(organizerService.se)//todo get organizer email for each service
                System.out.println(returnValues);
            }
        }
    }
}
