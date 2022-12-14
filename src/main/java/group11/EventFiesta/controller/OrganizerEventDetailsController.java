package group11.EventFiesta.controller;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.EventManager;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.MailProtocol;
import group11.EventFiesta.mail.SSLSMTPProtocol;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class OrganizerEventDetailsController {

    @GetMapping("/getOrganizerDetails")
    public String getOrganizerDetails(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Integer organizerId = Integer.parseInt(session.getAttribute("accountId").toString());
            System.out.println("organizerId: " + organizerId);
            String status = "Complete";
            IDBPersistence idbPersistence = new MySQLDBPersistence();
            Object[] params = new Object[]{organizerId, status};
            List<Map<String, Object>> eventDetails = idbPersistence.loadData("getOrganizerEventDetails", params);
            for (Map<String, Object> event : eventDetails) {
                Long eventId = Long.parseLong(event.get("event_id").toString());
                params= new Object[]{eventId, status, organizerId};
                List<Map<String, Object>> serviceDetails = idbPersistence.loadData("getOrganizerEventServices", params);
                for (Map<String, Object> service : serviceDetails) {
                    Long serviceId = Long.parseLong(service.get("service_id").toString());
                    params= new Object[]{eventId, serviceId};
                    List<Map<String, Object>> reviewDetails = idbPersistence.loadData("getServiceReviews", params);
                    if (reviewDetails.size() > 0) {
                        service.putAll(reviewDetails.get(0));
                    } else {
                        service.put("review", "-");
                        service.put("rating", "-");
                    }
                }
                event.put("services", serviceDetails);
                System.out.println(serviceDetails);
            }
            model.addAttribute("CompletedEvents", new ArrayList<>(eventDetails));


            status = "Upcoming";
            EventManager eventManager = new EventManager(idbPersistence);
            List<Map<String, Object>> events = eventManager.getEventServices(organizerId, status);
            model.addAttribute("UpcomingEvents", new ArrayList<>(events));

            status = "Pending";
            events = eventManager.getEventServices(organizerId, status);
            model.addAttribute("PendingEvents", new ArrayList<>(events));
        } catch (Exception exception) {
            System.out.println("Exception in getOrganizerDetails: " + exception.getMessage());
            exception.printStackTrace();
        }
        return "OrganizerDetails";
    }

    @PostMapping("/acceptEvent")
    public String acceptEvent(Model model, @RequestParam("event_id") Integer eventId, @RequestParam("client_email") String email, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long organizerId = Long.parseLong(session.getAttribute("accountId").toString());
            Object[] params = new Object[]{eventId, organizerId, "Upcoming"};
            String mailSubject = "Event Fiesta - Event confirmed!";
            String mailBody = "Event has been confirmed by the Organizer";
            MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            Mail mail = new Mail(email, mailSubject, mailBody);
            IDBPersistence idbPersistence = new MySQLDBPersistence();
            EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
            eventManager.updateEvent(params, mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/getOrganizerDetails";
    }

    @PostMapping("/rejectEvent")
    public String rejectEvent(Model model, @RequestParam("event_id") Integer eventId, @RequestParam("client_email") String email, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long organizerId = Long.parseLong(session.getAttribute("accountId").toString());
            Object[] params = new Object[]{eventId, organizerId, "Rejected"};
            String mailSubject = "Event Fiesta - Event not confirmed!";
            String mailBody = "Event was rejected by the Organizer";
            MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            Mail mail = new Mail(email, mailSubject, mailBody);
            IDBPersistence idbPersistence = new MySQLDBPersistence();
            EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
            eventManager.updateEvent(params, mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/getOrganizerDetails";
    }
}
