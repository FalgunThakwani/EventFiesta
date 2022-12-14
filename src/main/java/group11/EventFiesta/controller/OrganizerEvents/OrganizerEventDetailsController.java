package group11.EventFiesta.controller.OrganizerEvents;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.status.*;
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

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatus completeStatus = createEventStatus.getEventStatus("complete");
            List<Map<String, Object>> events = eventManager.getEventServices(organizerId, completeStatus.getEventStatus());
            model.addAttribute("CompletedEvents", new ArrayList<>(events));

            IEventStatus upcomingStatus = createEventStatus.getEventStatus("upcoming");
            events = eventManager.getEventServices(organizerId, upcomingStatus.getEventStatus());
            model.addAttribute("UpcomingEvents", new ArrayList<>(events));

            IEventStatus pendingStatus = createEventStatus.getEventStatus("pending");
            events = eventManager.getEventServices(organizerId, pendingStatus.getEventStatus());
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

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatusWithPreviousState newStatus = createEventStatus.getEventStatusWithPreviousStatus("upcoming");
            IEventStatus previousStatus = newStatus.getPreviousState();
            Object[] params = new Object[]{eventId, organizerId, newStatus.getEventStatus(), previousStatus.getEventStatus()};

            Mail mail = new Mail(email, newStatus.getEmailSubject(), newStatus.getEmailContent());
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

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatusWithPreviousState newStatus = createEventStatus.getEventStatusWithPreviousStatus("complete");
            IEventStatus previousStatus = newStatus.getPreviousState();
            Object[] params = new Object[]{eventId, organizerId, newStatus.getEventStatus(), previousStatus.getEventStatus()};

            Mail mail = new Mail(email, newStatus.getEmailSubject(), newStatus.getEmailContent());
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

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatusWithPreviousState newStatus = createEventStatus.getEventStatusWithPreviousStatus("reject");
            IEventStatus previousStatus = newStatus.getPreviousState();
            Object[] params = new Object[]{eventId, organizerId, newStatus.getEventStatus(), previousStatus.getEventStatus()};

            MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            Mail mail = new Mail(email, newStatus.getEmailSubject(), newStatus.getEmailContent());
            IDBPersistence idbPersistence = new MySQLDBPersistence();

            EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
            eventManager.updateEvent(params, mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/getOrganizerDetails";
    }
}
