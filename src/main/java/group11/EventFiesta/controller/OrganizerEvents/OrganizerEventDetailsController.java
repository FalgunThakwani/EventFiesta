package group11.EventFiesta.controller.OrganizerEvents;

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

            IDBPersistence idbPersistence = new MySQLDBPersistence();
            EventManager eventManager = new EventManager(idbPersistence);

            String status = "Complete";
            List<Map<String, Object>> events = eventManager.getEventServicesAndReviews(organizerId, status);
            model.addAttribute("CompletedEvents", new ArrayList<>(events));

            status = "Upcoming";
            events = eventManager.getEventServices(organizerId, status);
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
            String newStatus = "Upcoming";
            String prevStatus = "Pending";
            Object[] params = new Object[]{eventId, organizerId, newStatus, prevStatus};

            String mailSubject = "Event Fiesta - Event confirmed!";
            String mailBody = "Event has been confirmed by the Organizer";
            Mail mail = new Mail(email, mailSubject, mailBody);
            MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            IDBPersistence idbPersistence = new MySQLDBPersistence();

            EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
            eventManager.updateEvent(params, mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/getOrganizerDetails";
    }

    @PostMapping("/updateCompleted")
    public String updateEvenCompleted(Model model, @RequestParam("event_id") Integer eventId, @RequestParam("client_email") String email, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long organizerId = Long.parseLong(session.getAttribute("accountId").toString());
            String newStatus = "Complete";
            String prevStatus = "Upcoming";
            Object[] params = new Object[]{eventId, organizerId, newStatus, prevStatus};

            String mailSubject = "Event Fiesta - Event completed!";
            String mailBody = "Event has been marked completed by the Organizer. Contact us if you have an issue.";
            Mail mail = new Mail(email, mailSubject, mailBody);
            MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
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
            String newStatus = "Rejected";
            String prevStatus = "Pending";
            Object[] params = new Object[]{eventId, organizerId, newStatus, prevStatus};

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
