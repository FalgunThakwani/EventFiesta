package group11.EventFiesta.event.status;

public class EventUpcoming implements IEventStatusWithPreviousStatus {

    @Override
    public String getEventStatus() {
        return "Upcoming";
    }

    @Override
    public String getEmailSubject() {
        return "Event Fiesta - Event confirmed!";
    }

    @Override
    public String getEmailContent() {
        return "Event has been confirmed by the Organizer";
    }

    public IEventStatus getPreviousState() {
        return new EventPending();
    }
}
