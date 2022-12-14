package group11.EventFiesta.event.status;

public class EventPending implements IEventStatus {
    @Override
    public String getEventStatus() {
        return "Pending";
    }

    @Override
    public String getEmailSubject() {
        return "Event Fiesta - New Event - Action Required!";
    }

    @Override
    public String getEmailContent() {
        return "An event is waiting your approval. Please login to your account to take action.";
    }
}
