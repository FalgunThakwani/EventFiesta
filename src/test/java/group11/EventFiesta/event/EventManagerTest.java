package group11.EventFiesta.event;

import group11.EventFiesta.best5.DecoratedOrganizerService;
import group11.EventFiesta.best5.OrganizerGroup;
import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MockEventManagerDBPersistence;
import group11.EventFiesta.event.status.CreateEventStatus;
import group11.EventFiesta.event.status.IEventStatus;
import group11.EventFiesta.event.status.IEventStatusWithPreviousStatus;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.MailProtocol;
import group11.EventFiesta.mail.SSLSMTPProtocol;
import group11.EventFiesta.model.UserEventQuestionnaire;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventManagerTest {

    @Test
    public void updateEventSuccessTest() {
        try {

            try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
                when(mock.sendMail(any())).thenReturn(true);
            })) {
                IDBPersistence idbPersistence = new MockEventManagerDBPersistence();

                CreateEventStatus createEventStatus = new CreateEventStatus();
                IEventStatusWithPreviousStatus newStatus = createEventStatus.getEventStatusWithPreviousStatus("upcoming");
                IEventStatus previousStatus = newStatus.getPreviousState();

                Object[] params = new Object[]{1, 1, newStatus.getEventStatus(), previousStatus.getEventStatus()};

                Mail mail = new Mail("test@gmail.com", newStatus.getEmailSubject(), newStatus.getEmailContent());
                MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);

                EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
                Assertions.assertEquals(true, eventManager.updateEvent(params, mail));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void updateEventFailureTest() {
        try {

            try (MockedConstruction<Mail> mocked = Mockito.mockConstruction(Mail.class, (mock, context) -> {
                when(mock.sendMail(any())).thenReturn(true);
            })) {
                IDBPersistence idbPersistence = new MockEventManagerDBPersistence();

                CreateEventStatus createEventStatus = new CreateEventStatus();
                IEventStatusWithPreviousStatus newStatus = createEventStatus.getEventStatusWithPreviousStatus("upcoming");
                IEventStatus wrongPrevState = createEventStatus.getEventStatus("complete");

                Object[] params = new Object[]{1, 1, newStatus.getEventStatus(), wrongPrevState.getEventStatus()};

                Mail mail = new Mail("test@gmail.com", newStatus.getEmailSubject(), newStatus.getEmailContent());
                MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);

                EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
                Assertions.assertEquals(false, eventManager.updateEvent(params, mail));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void getEventServicesSuccessTest() {
        try {

            Integer organizerId = 1;

            IDBPersistence idbPersistence = new MockEventManagerDBPersistence();
            EventManager eventManager = new EventManager(idbPersistence);

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatus completeStatus = createEventStatus.getEventStatus("upcoming");

            List<Map<String, Object>> eventDetails = eventManager.getEventServices(organizerId, completeStatus.getEventStatus());

            Assertions.assertEquals(2, eventDetails.size());

            for (Map<String, Object> event : eventDetails) {

                List<Map<String, Object>> services = (List<Map<String, Object>>) event.get("services");
                Assertions.assertEquals(2, services.size());

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void getEventServicesCompletedReviewTest() {
        try {

            Integer organizerId = 1;

            IDBPersistence idbPersistence = new MockEventManagerDBPersistence();
            EventManager eventManager = new EventManager(idbPersistence);

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatus completeStatus = createEventStatus.getEventStatus("complete");

            List<Map<String, Object>> eventDetails = eventManager.getEventServices(organizerId, completeStatus.getEventStatus());

            Assertions.assertEquals(1, eventDetails.size());

            for (Map<String, Object> event : eventDetails) {

                List<Map<String, Object>> serviceDetails = (List<Map<String, Object>>) event.get("services");
                Assertions.assertEquals(1, serviceDetails.size());

                for (Map<String, Object> service : serviceDetails) {
                    Assertions.assertEquals("Good review", service.get("review"));
                    Assertions.assertEquals("5", service.get("rating"));
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void getEventServicesCompletedNoReviewTest() {
        try {

            Integer organizerId = 2;

            IDBPersistence idbPersistence = new MockEventManagerDBPersistence();
            EventManager eventManager = new EventManager(idbPersistence);

            CreateEventStatus createEventStatus = new CreateEventStatus();
            IEventStatus completeStatus = createEventStatus.getEventStatus("complete");

            List<Map<String, Object>> eventDetails = eventManager.getEventServices(organizerId, completeStatus.getEventStatus());

            Assertions.assertEquals(1, eventDetails.size());

            for (Map<String, Object> event : eventDetails) {

                List<Map<String, Object>> serviceDetails = (List<Map<String, Object>>) event.get("services");
                Assertions.assertEquals(1, serviceDetails.size());

                for (Map<String, Object> service : serviceDetails) {
                    Assertions.assertEquals("-", service.get("review"));
                    Assertions.assertEquals("-", service.get("rating"));
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void addEventTest() {

        try (MockedConstruction<Mail> mockMail = Mockito.mockConstruction(Mail.class, (mock, context) -> {
            when(mock.sendMail(any())).thenReturn(true);
        })) {
            UserEventQuestionnaire mockUserEventQuestionnaire = Mockito.mock(UserEventQuestionnaire.class);
            when(mockUserEventQuestionnaire.getCity()).thenReturn("Halifax");
            when(mockUserEventQuestionnaire.getProvince()).thenReturn("NS");
            when(mockUserEventQuestionnaire.getGuestCount()).thenReturn(100);
            when(mockUserEventQuestionnaire.getEvent()).thenReturn("Wedding");
            when(mockUserEventQuestionnaire.getBudget()).thenReturn(1000);
            when(mockUserEventQuestionnaire.getDateTime()).thenReturn("10 Dec");

            DecoratedOrganizerService decoratedService = Mockito.mock(DecoratedOrganizerService.class);
            when(decoratedService.getId()).thenReturn(1);
            when(decoratedService.getServiceName()).thenReturn("Decoration");

            OrganizerGroup selectedGroup = new OrganizerGroup();
            selectedGroup.add(decoratedService);

            Integer user_id = 1;

            MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);
            IDBPersistence idbPersistence = new MockEventManagerDBPersistence();

            EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
            eventManager.addEvent(mockUserEventQuestionnaire, selectedGroup, user_id);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
