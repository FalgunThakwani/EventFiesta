package group11.EventFiesta.event.status;

public class CreateEventStatus {

    public IEventStatusWithPreviousStatus getEventStatusWithPreviousStatus(String action) {
        switch (action) {
            case "upcoming":
                return new EventUpcoming();
            case "reject":
                return new EventRejected();
            case "complete":
                return new EventComplete();
            default:
                return new EventUpcoming();
        }
    }

    public IEventStatus getEventStatus(String action) {
        switch (action) {
            case "upcoming":
                return new EventUpcoming();
            case "reject":
                return new EventRejected();
            case "complete":
                return new EventComplete();
            case "pending":
                return new EventPending();
            default:
                return new EventPending();
        }
    }
}
