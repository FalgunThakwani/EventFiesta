package group11.EventFiesta.event.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateEventStatusTest {

    @Test
    public void getCompleteEventStatusTest() {
        CreateEventStatus createEventStatus = new CreateEventStatus();
        IEventStatus completeEventStatus = createEventStatus.getEventStatus("complete");

        String expectedStatus = "Complete";
        String expectedMailSubject = "Event Fiesta - Event completed!";
        String expectedMailContent = "Event has been marked complete by the Organizer. Contact us if you have an issue.";

        Assertions.assertEquals(expectedStatus, completeEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, completeEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, completeEventStatus.getEmailContent());

        IEventStatusWithPreviousStatus completeEventStatusWithPrev = createEventStatus.getEventStatusWithPreviousStatus("complete");
        IEventStatus upcomingStatus = new EventUpcoming();
        IEventStatus prevStatus = completeEventStatusWithPrev.getPreviousState();

        Assertions.assertEquals(upcomingStatus.getEventStatus(), prevStatus.getEventStatus());
        Assertions.assertEquals(expectedStatus, completeEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, completeEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, completeEventStatus.getEmailContent());

    }

    @Test
    public void getUpcomingEventStatusTest() {
        CreateEventStatus createEventStatus = new CreateEventStatus();
        IEventStatus upcomingEventStatus = createEventStatus.getEventStatus("upcoming");

        String expectedStatus = "Upcoming";
        String expectedMailSubject = "Event Fiesta - Event confirmed!";
        String expectedMailContent = "Event has been confirmed by the Organizer";

        Assertions.assertEquals(expectedStatus, upcomingEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, upcomingEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, upcomingEventStatus.getEmailContent());

        IEventStatusWithPreviousStatus completeEventStatusWithPrev = createEventStatus.getEventStatusWithPreviousStatus("upcoming");
        IEventStatus pending = new EventPending();
        IEventStatus prevStatus = completeEventStatusWithPrev.getPreviousState();

        Assertions.assertEquals(pending.getEventStatus(), prevStatus.getEventStatus());
        Assertions.assertEquals(expectedStatus, upcomingEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, upcomingEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, upcomingEventStatus.getEmailContent());
    }

    @Test
    public void getRejectedEventStatusTest() {
        CreateEventStatus createEventStatus = new CreateEventStatus();
        IEventStatus rejectedEventStatus = createEventStatus.getEventStatus("reject");

        String expectedStatus = "Rejected";
        String expectedMailSubject = "Event Fiesta - Event not confirmed!";
        String expectedMailContent = "Unfortunately the organizer rejected your event.";

        Assertions.assertEquals(expectedStatus, rejectedEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, rejectedEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, rejectedEventStatus.getEmailContent());

        IEventStatusWithPreviousStatus completeEventStatusWithPrev = createEventStatus.getEventStatusWithPreviousStatus("reject");
        IEventStatus pending = new EventPending();
        IEventStatus prevStatus = completeEventStatusWithPrev.getPreviousState();

        Assertions.assertEquals(pending.getEventStatus(), prevStatus.getEventStatus());
        Assertions.assertEquals(expectedStatus, rejectedEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, rejectedEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, rejectedEventStatus.getEmailContent());
    }

    @Test
    public void getPendingEventStatusTest() {

        CreateEventStatus createEventStatus = new CreateEventStatus();
        IEventStatus pendingEventStatus = createEventStatus.getEventStatus("pending");

        String expectedStatus = "Pending";
        String expectedMailSubject = "Event Fiesta - New Event - Action Required!";
        String expectedMailContent = "An event is waiting your approval. Please login to your account to take action.";

        Assertions.assertEquals(expectedStatus, pendingEventStatus.getEventStatus());
        Assertions.assertEquals(expectedMailSubject, pendingEventStatus.getEmailSubject());
        Assertions.assertEquals(expectedMailContent, pendingEventStatus.getEmailContent());

    }
}
