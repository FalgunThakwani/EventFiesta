package group11.EventFiesta.event;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.MailProtocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    IDBPersistence idbPersistence;
    public EventManager(IDBPersistence idbPersistence) {
        this.idbPersistence = idbPersistence;
    }

    public void updateEvent(Object [] params, Mail mail, MailProtocol mailProtocol) {
        try {
            Integer rows = idbPersistence.updateData("updateEventStatus", params);
            if (rows >= 1) {
                mail.sendMail(mailProtocol);
            }
            System.out.println("Updated rows: " + rows);
        } catch (Exception e) {
            System.out.println("Exception in EventManager.updateEvent() " + e.getMessage());
        }
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
}
