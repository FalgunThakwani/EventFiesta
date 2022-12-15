package group11.EventFiesta.event.status;

public class EventComplete implements IEventStatusWithPreviousStatus {

    @Override
    public String getEventStatus() {
        return "Complete";
    }

    @Override
    public String getEmailSubject() {
        return "Event Fiesta - Event completed!";
    }

    @Override
    public String getEmailContent() {
        return "Event has been marked complete by the Organizer. Contact us if you have an issue.";
    }

    public IEventStatus getPreviousState() {
        return new EventUpcoming();
    }
}
