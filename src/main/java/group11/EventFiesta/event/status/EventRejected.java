package group11.EventFiesta.event.status;

public class EventRejected  implements IEventStatusWithPreviousState {

    @Override
    public String getEventStatus() {
        return "Rejected";
    }

    @Override
    public String getEmailSubject() {
        return "Event Fiesta - Event not confirmed!";
    }

    @Override
    public String getEmailContent() {
        return "Unfortunately the organizer rejected your event.";
    }

    public IEventStatus getPreviousState() {
        return new EventPending();
    }
}
