package group11.EventFiesta.event;

import group11.EventFiesta.best5.DecoratedOrganizerService;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.best5.GroupComponent;
import group11.EventFiesta.best5.OrganizerGroup;
import group11.EventFiesta.event.status.CreateEventStatus;
import group11.EventFiesta.event.status.EventComplete;
import group11.EventFiesta.event.status.IEventStatus;
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

    public boolean updateEvent(Object[] params, Mail mail) throws Exception {
        Integer rows = idbPersistence.updateData("updateEventStatus", params);
        if (rows >= 1) {
            mail.sendMail(mailProtocol);
            return true;
        }
        System.out.println("Updated rows: " + rows);
        return false;
    }

    public List<Map<String, Object>> getEventServices(Integer organizerId, String status) throws Exception {

        Object[] params = new Object[] { organizerId, status };
        List<Map<String, Object>> eventDetails = idbPersistence.loadData("getOrganizerEventDetails", params);

        for (Map<String, Object> event : eventDetails) {
            Integer eventId = Integer.parseInt(event.get("event_id").toString());
            params = new Object[] { eventId, status, organizerId };
            List<Map<String, Object>> serviceDetails = idbPersistence.loadData("getOrganizerEventServices", params);
            if (status.equals(new EventComplete().getEventStatus())) {
                getServiceReviews(eventId, serviceDetails);
            }
            event.put("services", serviceDetails);
        }

        return eventDetails;
    }

    private void getServiceReviews(Integer eventId, List<Map<String, Object>> serviceDetails) throws Exception {

        for (Map<String, Object> service : serviceDetails) {
            Long serviceId = Long.parseLong(service.get("service_id").toString());
            Object [] params = new Object[]{eventId, serviceId};
            List<Map<String, Object>> reviewDetails = idbPersistence.loadData("getServiceReviews", params);

            if (reviewDetails.size() > 0) {
                service.putAll(reviewDetails.get(0));
            } else {
                service.put("review", "-");
                service.put("rating", "-");
            }
        }
    }

    public void addEvent(UserEventQuestionnaire eventDetails, OrganizerGroup selectedGroup, Integer userId) throws Exception {

        String venue = eventDetails.getCity() + ", " + eventDetails.getProvince();
        Object[] params = new Object[] { userId, eventDetails.getEvent(), venue, eventDetails.getDateTime(),
                eventDetails.getBudget(), eventDetails.getGuestCount() };
        int[] outParams = new int[] { Types.INTEGER };
        List<Object> returnValues = idbPersistence.insertData("addEvent", params, outParams);
        System.out.println(returnValues);

        if (returnValues != null && returnValues.size() > 0) {

            Integer eventId = Integer.parseInt(returnValues.get(0).toString());
            for (GroupComponent organizerService : selectedGroup.getOrganizerServices()) {

                DecoratedOrganizerService service = (DecoratedOrganizerService) organizerService;
                addService(eventId, service);
            }
        }
    }

    private void addService(Integer eventId, DecoratedOrganizerService service) throws Exception {

        CreateEventStatus createEventStatus = new CreateEventStatus();
        IEventStatus pendingStatus = createEventStatus.getEventStatus("pending");
        Object[] params = new Object[]{eventId, service.getId(), service.getPrice(), pendingStatus.getEventStatus()};

        int[] outParams = new int[]{};
        idbPersistence.insertData("addService", params, outParams);

        String mailRecipent = service.getEmail();
        Mail mail = new Mail(mailRecipent, pendingStatus.getEmailSubject(), pendingStatus.getEmailContent());
        mail.sendMail(mailProtocol);
    }
}
